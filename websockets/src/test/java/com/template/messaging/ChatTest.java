package com.template.messaging;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatTest {

  @LocalServerPort private int port;

  private RestTemplate restTemplate = new RestTemplate();

  private WebSocketStompClient stompClient;

  @BeforeEach
  void setup() {
    List<Transport> transports = new ArrayList<>();
    transports.add(new WebSocketTransport(new StandardWebSocketClient()));
    transports.add(new RestTemplateXhrTransport()); // fallback transport

    SockJsClient sockJsClient = new SockJsClient(transports);
    stompClient = new WebSocketStompClient(sockJsClient);
    stompClient.setMessageConverter(new MappingJackson2MessageConverter());
  }

  @Test
  @WithMockUser(roles = "USER", username = "user")
  void testMultipleUsersReceiveCorrectMessages() throws Exception {
    int userCount = 3;
    List<String> usernames = List.of("Alice", "Bob", "Charlie");

    // Each user's received messages
    List<List<Map<String, Object>>> receivedMessages = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(userCount * userCount);

    List<StompSession> sessions = new ArrayList<>();

    // Connect all users and subscribe to /topic/messages
    for (int i = 0; i < userCount; i++) {
      receivedMessages.add(Collections.synchronizedList(new ArrayList<>()));

      int userIndex = i;
      sessions.add(
          connectAndSubscribe(
              "/topic/messages",
              payload -> {
                receivedMessages.get(userIndex).add(payload);
                latch.countDown();
              }));
    }

    // Send one message per user
    for (int i = 0; i < userCount; i++) {
      String text = "Hello from " + usernames.get(i);
      Map<String, String> msg = Map.of("from", usernames.get(i), "text", text);
      sessions.get(i).send("/app/chat", msg);
    }

    // Wait for all messages
    boolean allReceived = latch.await(5, TimeUnit.SECONDS);
    assertThat(allReceived).isTrue();

    // Assert that each user received all messages and the content is correct
    for (List<Map<String, Object>> msgs : receivedMessages) {
      assertThat(msgs).hasSize(userCount);

      // Verify each username appears once
      Set<String> senders = new HashSet<>();
      for (Map<String, Object> msg : msgs) {
        senders.add((String) msg.get("from"));
        assertThat(msg.get("text")).as("Message text should not be null").isNotNull();
        assertThat(msg.get("time")).as("Timestamp should not be null").isNotNull();
      }
      assertThat(senders).containsExactlyInAnyOrderElementsOf(usernames);
    }
  }

  private StompSession connectAndSubscribe(
      String topic, java.util.function.Consumer<Map<String, Object>> consumer) throws Exception {
    List<Transport> transports = new ArrayList<>();
    transports.add(new WebSocketTransport(new StandardWebSocketClient()));
    transports.add(new RestTemplateXhrTransport()); // Add fallback

    SockJsClient sockJsClient = new SockJsClient(transports);
    WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
    stompClient.setMessageConverter(new MappingJackson2MessageConverter());

    // prepare headers with session cookie so the ws calls will be authenticated
    String sessionCookie = loginAndGetSessionCookie();
    WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
    webSocketHttpHeaders.add(HttpHeaders.COOKIE, sessionCookie);

    CompletableFuture<StompSession> futureSession = new CompletableFuture<>();
    stompClient.connectAsync(
        "http://localhost:" + port + "/chat",
        webSocketHttpHeaders,
        new StompSessionHandlerAdapter() {
          @Override
          public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            futureSession.complete(session);
          }

          @Override
          public void handleTransportError(StompSession session, Throwable exception) {
            futureSession.completeExceptionally(exception);
          }
        });

    StompSession session = futureSession.get(5, TimeUnit.SECONDS);

    session.subscribe(
        topic,
        new StompFrameHandler() {
          @Override
          public Type getPayloadType(StompHeaders headers) {
            return Map.class;
          }

          @Override
          public void handleFrame(StompHeaders headers, Object payload) {
            consumer.accept((Map<String, Object>) payload);
          }
        });

    return session;
  }

  // used to get the http session cookie
  private String loginAndGetSessionCookie() {
    String loginUrl = "http://localhost:" + port + "/login";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
    form.add("username", "user");
    form.add("password", "user");

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

    ResponseEntity<String> response = restTemplate.postForEntity(loginUrl, request, String.class);

    // Extract JSESSIONID cookie from response headers
    String cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

    if (cookie == null) {
      throw new IllegalStateException("No Set-Cookie header found on login response");
    }

    // The cookie may contain attributes like Path, HttpOnly; only pass the cookie itself
    return cookie.split(";")[0];
  }
}

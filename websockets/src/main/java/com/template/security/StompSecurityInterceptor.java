package com.template.security;

import java.security.Principal;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

@Configuration
public class StompSecurityInterceptor implements ChannelInterceptor {

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
      System.out.println("STOMP CONNECT: headers=" + accessor.toNativeHeaderMap());
    }

    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
      Principal user = accessor.getUser(); // Populated from Spring Security session
      if (user == null) {
        throw new IllegalStateException("User not authenticated via session");
      }
    }

    return message;
  }
}

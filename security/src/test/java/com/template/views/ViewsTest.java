package com.template.views;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ViewsTest {

  @Autowired private MockMvc mvc;

  @Test
  public void getLogin() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .string(
                    containsString(
                        "<label> Password: <input type=\"password\" name=\"password\"/> </label>")));
  }

  @Test
  public void getHome() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/home").with(user("user")).accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("here</a> to see a greeting.</p>")));
  }

  @Test
  public void getHello() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/hello").with(user("user")).accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("From Spring Boot")));
  }
}

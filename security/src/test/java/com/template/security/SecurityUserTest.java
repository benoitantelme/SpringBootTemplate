package com.template.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest()
@AutoConfigureMockMvc
public class SecurityUserTest {

  @Autowired private MockMvc mvc;

  @WithMockUser(roles = "USER", username = "user")
  @Test
  void testHomeRightPw() throws Exception {
    mvc.perform(get("/")).andExpect(status().isOk());
  }

  @Test
  void testHomeWrongPw() throws Exception {
    // 302 for login redirection, http basic does not work here actually
    mvc.perform(get("/")).andExpect(status().isFound());
  }
}

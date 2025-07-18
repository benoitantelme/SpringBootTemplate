package com.template.actuators;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ActuatorsTest {

  @Autowired private MockMvc mvc;

  @WithMockUser(roles = "USER", username = "user")
  @Test
  public void getActuators() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/actuator").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("health")))
        .andExpect(content().string(containsString("env")))
        .andExpect(content().string(containsString("metrics")))
        .andExpect(content().string(containsString("configprops")))
        .andExpect(content().string(containsString("beans")));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  public void getEnv() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/actuator/env").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("activeProfiles")))
        .andExpect(content().string(containsString("java.vm.vendor")));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  public void getMetrics() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/actuator/metrics").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("jvm.buffer.memory.used")))
        .andExpect(content().string(containsString("system.cpu.count")));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  public void getBeans() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/actuator/beans").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("defaultServletHandlerMapping")));
  }
}

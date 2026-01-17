package com.template.actuators;

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
public class ActuatorsTest {

  @Autowired private MockMvc mvc;

  @Test
  public void getActuators() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.get("/actuator")
                .with(user("user"))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("health")))
        .andExpect(content().string(containsString("env")))
        .andExpect(content().string(containsString("metrics")))
        .andExpect(content().string(containsString("configprops")))
        .andExpect(content().string(containsString("beans")));
  }

  @Test
  public void getEnv() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.get("/actuator/env")
                .with(user("user"))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("activeProfiles")))
        .andExpect(content().string(containsString("java.vm.vendor")));
  }

  @Test
  public void getMetrics() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.get("/actuator/metrics")
                .with(user("user"))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("jvm.buffer.memory.used")))
        .andExpect(content().string(containsString("system.cpu.count")));
  }

  @Test
  public void getBeans() throws Exception {
    mvc.perform(
            MockMvcRequestBuilders.get("/actuator/beans")
                .with(user("user"))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("defaultServletHandlerMapping")));
  }
}

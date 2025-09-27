package com.template.books;

import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.books.model.Counterparty;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CounterpartiesControllerTest {

  @Autowired private MockMvc mvc;

  @WithMockUser(roles = "USER", username = "user")
  @Test
  @Order(1)
  public void getCounterparty() throws Exception {
    String CPTY = "BNP";
    mvc.perform(
            MockMvcRequestBuilders.get("/counterparty/" + CPTY).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(CPTY)));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  @Order(2)
  public void getCounterparties() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/counterparties").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(3)));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  @Order(3)
  public void postCounterparty() throws Exception {
    String TEST = "Test";
    Counterparty cpty = new Counterparty(TEST);
    mvc.perform(
            MockMvcRequestBuilders.post("/counterparty")
                .with(csrf())
                .content(new ObjectMapper().writeValueAsString(cpty))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    mvc.perform(
            MockMvcRequestBuilders.get("/counterparty/" + TEST).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(TEST)));
  }
}

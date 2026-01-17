package com.template.trades;

import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.template.counterparties.model.Counterparty;
import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TradeControllerTest {

  @Autowired private MockMvc mvc;

  @WithMockUser(roles = "USER", username = "user")
  @Test
  @Order(1)
  public void getTrade() throws Exception {
    String firstName = "FirstTrade";
    mvc.perform(
            MockMvcRequestBuilders.get("/trade/" + firstName).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(firstName)));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  @Order(2)
  public void getTrades() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/trades").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(2)));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  @Order(3)
  public void postTrade() throws Exception {
    String THIRD = "ThirdTrade";
    Counterparty bnp = new Counterparty("BNP");
    Trade trade = new Trade(THIRD, bnp, 111, Currency.EUR);

    mvc.perform(
            MockMvcRequestBuilders.post("/trade")
                .with(csrf())
                .content(new ObjectMapper().writeValueAsString(trade))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    mvc.perform(MockMvcRequestBuilders.get("/trade/" + THIRD).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(THIRD)))
        .andExpect(jsonPath("$.counterparty.name", is(bnp.getName())));
  }
}

package com.template.trades;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.counterparties.model.Counterparty;
import com.template.counterparties.service.CounterpartyService;
import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import org.junit.jupiter.api.MethodOrderer;
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
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TradeControllerTest {

  @Autowired private MockMvc mvc;

  @Autowired private CounterpartyService counterpartyService;

  @WithMockUser(roles = "USER", username = "user")
  @Test
  public void getTrades() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/trades").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(2)));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  public void getTrade() throws Exception {
    String firstName = "FirstTrade";
    mvc.perform(
            MockMvcRequestBuilders.get("/trade/" + firstName).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(firstName)));
  }

  @WithMockUser(roles = "USER", username = "user")
  @Test
  public void postTrade() throws Exception {
    String THIRD = "ThirdTrade";
    Counterparty bnp = counterpartyService.getOrCreate("BNP");
    Trade trade = new Trade(THIRD, bnp, 111, Currency.EUR);

    mvc.perform(
            MockMvcRequestBuilders.post("/trade")
                .content(new ObjectMapper().writeValueAsString(trade))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    mvc.perform(MockMvcRequestBuilders.get("/trade/" + THIRD).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(THIRD)))
        .andExpect(jsonPath("$.counterparty.name", is(bnp.getName())));
  }
}

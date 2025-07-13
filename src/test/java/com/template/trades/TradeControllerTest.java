package com.template.trades;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import com.template.trades.repository.TradeRepository;
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
public class TradeControllerTest {
  @Autowired public TradeRepository tradeRepository;
  ;

  @Autowired private MockMvc mvc;

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
    String TESTTRADE = "testtrade";
    Trade t1 = new Trade(TESTTRADE, "AAA", 11d, Currency.EUR);
    tradeRepository.save(t1);

    mvc.perform(
            MockMvcRequestBuilders.get("/trade/" + TESTTRADE).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(t1.getName()))) // is(<Object>) is e.g. a Hamcrest Matcher
        .andExpect(jsonPath("$.counterparty", is(t1.getCounterparty())))
        .andExpect(jsonPath("$.amount", is(t1.getAmount())))
        .andExpect(jsonPath("$.currency", is(t1.getCurrency().toString())));
  }
}

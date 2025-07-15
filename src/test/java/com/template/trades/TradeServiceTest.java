package com.template.trades;

import static org.junit.jupiter.api.Assertions.*;

import com.template.counterparties.model.Counterparty;
import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import com.template.trades.service.TradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradeServiceTest {

  @Autowired TradeService tradeService;

  @Test
  void getTrades() {
    Trade t1 = new Trade("SWAP", new Counterparty("BNP"), 280.000d, Currency.EUR);
    Trade t2 = new Trade("BOND", new Counterparty("HSBC"), 52640.000d, Currency.GBP);
    tradeService.saveTrade(t1);
    tradeService.saveTrade(t2);

    var result = tradeService.findTrades();

    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(4, result.size());

    Trade savedT1 = result.get(2);
    Trade savedT2 = result.get(3);

    assertEquals(t1.getName(), savedT1.getName());
    assertEquals(t1.getAmount(), savedT1.getAmount());
    assertEquals(t1.getCounterparty(), savedT1.getCounterparty());
    assertEquals(t1.getCurrency(), savedT1.getCurrency());

    assertNotNull(savedT2.getName());
    assertEquals(t2.getName(), savedT2.getName());
    assertEquals(t2.getAmount(), savedT2.getAmount());
    assertEquals(t2.getCounterparty(), savedT2.getCounterparty());
    assertEquals(t2.getCurrency(), savedT2.getCurrency());
  }

  @Test
  void getTrade() {
    Trade t1 = new Trade("testtrade", new Counterparty("AAA"), 11d, Currency.EUR);
    tradeService.saveTrade(t1);

    var result = tradeService.findTrade("testtrade");
    assertNotNull(result);
    assertTrue(result.isPresent());

    var trade = result.get();
    assertEquals(t1.getName(), trade.getName());
    assertEquals(t1.getAmount(), trade.getAmount());
    assertEquals(t1.getCounterparty(), trade.getCounterparty());
    assertEquals(t1.getCurrency(), trade.getCurrency());
  }
}

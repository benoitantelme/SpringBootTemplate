package com.template.trades;

import static org.junit.jupiter.api.Assertions.*;

import com.template.books.model.Counterparty;
import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import com.template.trades.service.TradeService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TradeServiceTest {

  @Autowired TradeService tradeService;

  @Test
  @Order(1)
  void getTrade() {
    Counterparty test = new Counterparty("Test");
    Trade t1 = new Trade("testtrade", test, 11d, Currency.EUR);
    tradeService.saveTrade(t1);

    var result = tradeService.findTrade("testtrade");
    assertNotNull(result);
    assertTrue(result.isPresent());

    var trade = result.get();
    assertEquals(t1.getName(), trade.getName());
    assertEquals(t1.getAmount(), trade.getAmount());
    assertEquals(t1.getCounterparty().getName(), trade.getCounterparty().getName());
    assertEquals(t1.getCurrency(), trade.getCurrency());
  }

  @Test
  @Order(2)
  void getTrades() {
    Counterparty bnp = new Counterparty("BNP");
    Counterparty hsbc = new Counterparty("HSBC");

    Trade t1 = new Trade("SWAP", bnp, 280.000d, Currency.EUR);
    Trade t2 = new Trade("BOND", hsbc, 52640.000d, Currency.GBP);
    tradeService.saveTrade(t1);
    tradeService.saveTrade(t2);

    var result = tradeService.findTrades();

    assertNotNull(result);
    assertFalse(result.isEmpty());
    // plus the added trade from previous test
    assertEquals(5, result.size());

    Trade savedT1 = result.get(3);
    Trade savedT2 = result.get(4);

    assertEquals(t1.getName(), savedT1.getName());
    assertEquals(t1.getAmount(), savedT1.getAmount());
    assertEquals(t1.getCounterparty().getName(), savedT1.getCounterparty().getName());
    assertEquals(t1.getCurrency(), savedT1.getCurrency());

    assertNotNull(savedT2.getName());
    assertEquals(t2.getName(), savedT2.getName());
    assertEquals(t2.getAmount(), savedT2.getAmount());
    assertEquals(t2.getCounterparty().getName(), savedT2.getCounterparty().getName());
    assertEquals(t2.getCurrency(), savedT2.getCurrency());
  }
}

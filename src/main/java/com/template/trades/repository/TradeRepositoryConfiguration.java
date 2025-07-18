package com.template.trades.repository;

import com.template.counterparties.model.Counterparty;
import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import com.template.trades.service.TradeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TradeRepositoryConfiguration {

  @Bean
  public CommandLineRunner tradeDbSetup(TradeService tradeService) {
    return (args) -> {
      // initial trades
      Counterparty bnp = new Counterparty("BNP");
      Counterparty hsbc = new Counterparty("HSBC");

      tradeService.saveTrade(new Trade("FirstTrade", bnp, 280000, Currency.EUR));
      tradeService.saveTrade(new Trade("'SecondTrade", hsbc, 52640000, Currency.GBP));
    };
  }
}

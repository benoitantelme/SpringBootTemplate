package com.template.trades.repository;

import com.template.counterparties.model.Counterparty;
import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TradeRepositoryConfiguration {

  @Bean
  public CommandLineRunner tradeDbSetup(TradeRepository repository) {
    return (args) -> {
      // initial trades
      repository.save(new Trade("FirstTrade", new Counterparty("BNP"), 280000, Currency.EUR));
      repository.save(new Trade("'SecondTrade", new Counterparty("HSBC"), 52640000, Currency.GBP));
    };
  }
}

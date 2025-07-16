package com.template.trades.repository;

import com.template.counterparties.model.Counterparty;
import com.template.counterparties.repository.CounterpartyRepository;
import com.template.trades.model.Currency;
import com.template.trades.model.Trade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TradeRepositoryConfiguration {

  @Bean
  public CommandLineRunner tradeDbSetup(
      TradeRepository repository, CounterpartyRepository counterpartyRepository) {
    return (args) -> {
      // initial trades

      String bnpString = "BNP";
      String hsbcString = "HSBC";
      Counterparty bnp =
          counterpartyRepository.findByName(bnpString).orElse(new Counterparty(bnpString));
      Counterparty hsbc =
          counterpartyRepository.findByName(hsbcString).orElse(new Counterparty(hsbcString));

      repository.save(new Trade("FirstTrade", bnp, 280000, Currency.EUR));
      repository.save(new Trade("'SecondTrade", hsbc, 52640000, Currency.GBP));
    };
  }
}

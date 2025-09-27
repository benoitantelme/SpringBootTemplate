package com.template.books.repository;

import com.template.books.model.Counterparty;
import com.template.books.service.CounterpartyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterpartyRepositoryConfiguration {

  @Bean
  public CommandLineRunner counterpartyRepositorySetup(CounterpartyService counterpartyService) {
    return (args) -> {
      // initial cpties

      counterpartyService.saveCounterparty(new Counterparty("HSBC"));
      counterpartyService.saveCounterparty(new Counterparty("BNP"));
      counterpartyService.saveCounterparty(new Counterparty("UBS"));
    };
  }
}

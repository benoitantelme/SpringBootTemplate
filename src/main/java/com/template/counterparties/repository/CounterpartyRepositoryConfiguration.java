package com.template.counterparties.repository;

import com.template.counterparties.model.Counterparty;
import com.template.counterparties.service.CounterpartyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterpartyRepositoryConfiguration {

  @Bean
  public CommandLineRunner counterpartyRepositorySetup(CounterpartyService counterpartyService) {
    return (args) -> {
      // initial cpties

      counterpartyService.updateOrInsert(new Counterparty("HSBC"));
      counterpartyService.updateOrInsert(new Counterparty("BNP"));
      counterpartyService.updateOrInsert(new Counterparty("UBS"));
    };
  }
}

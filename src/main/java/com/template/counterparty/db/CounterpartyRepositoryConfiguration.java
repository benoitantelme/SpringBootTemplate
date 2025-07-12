package com.template.counterparty.db;

import com.template.counterparty.model.Counterparty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterpartyRepositoryConfiguration {

  Logger logger = LoggerFactory.getLogger(CounterpartyRepositoryConfiguration.class);

  @Bean
  public CommandLineRunner demo(CounterpartyRepository repository) {
    return (args) -> {
      // initial cpties
      repository.save(new Counterparty("HSBC"));
      repository.save(new Counterparty("BNP"));
      repository.save(new Counterparty("UBS"));
    };
  }
}

package com.template.trades.db;

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
            repository.save(new Trade("First Trade", "BNP", 280000, Currency.EUR));
            repository.save(new Trade("'Second Trade' Trade", "'HSBC'", 52640000, Currency.GBP));
        };
    }

}

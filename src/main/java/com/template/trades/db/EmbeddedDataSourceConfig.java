package com.template.trades.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
public class EmbeddedDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setDatabaseConfigurer(EmbeddedDatabaseConfigurers
                        .customizeConfigurer(H2, this::customize))
                .addScripts("db/sql/create_trade_schema.sql", "db/sql/initial_data.sql")
                .build();
    }

    private EmbeddedDatabaseConfigurer customize(EmbeddedDatabaseConfigurer defaultConfigurer) {
        return new EmbeddedDatabaseConfigurerDelegate(defaultConfigurer) {
            @Override
            public void configureConnectionProperties(ConnectionProperties properties, String databaseName) {
                super.configureConnectionProperties(properties, databaseName);
            }
        };
    }

}

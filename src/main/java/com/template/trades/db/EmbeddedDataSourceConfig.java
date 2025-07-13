package com.template.trades.db;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.*;

@Configuration
public class EmbeddedDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setDatabaseConfigurer(EmbeddedDatabaseConfigurers.customizeConfigurer(H2, this::customize))
                .build();
    }

    private EmbeddedDatabaseConfigurer customize(EmbeddedDatabaseConfigurer defaultConfigurer) {
        return new EmbeddedDatabaseConfigurerDelegate(defaultConfigurer) {
            @Override
            public void configureConnectionProperties(
                    ConnectionProperties properties, String databaseName) {
                super.configureConnectionProperties(properties, databaseName);
            }
        };
    }
}

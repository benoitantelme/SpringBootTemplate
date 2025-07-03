//package com.template.db.trades.config;
//
//import com.template.db.trades.TradesRepository;
//import com.template.db.trades.model.Trade;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
//@Configuration
//@EnableJpaRepositories(basePackageClasses = TradesRepository.class)
//@EntityScan(basePackageClasses = Trade.class)
//public class EmbeddedPostgresConfiguration {
//
//    private static EmbeddedPostgres embeddedPostgres;
//
//    @Bean
//    public DataSource dataSource() throws IOException {
//        embeddedPostgres = EmbeddedPostgres.builder()
//                .setImage(DockerImageName.parse("postgres:17.5"))
//                .start();
//
//        return embeddedPostgres.getPostgresDatabase();
//    }
//
//    public static class EmbeddedPostgresExtension implements AfterAllCallback {
//        @Override
//        public void afterAll(ExtensionContext context) throws Exception {
//            if (embeddedPostgres == null) {
//                return;
//            }
//            embeddedPostgres.close();
//        }
//    }
//
//}

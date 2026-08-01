package dev.bozlak.on_muhasebe_spring_boot.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.dialect.JdbcDialect;
import org.springframework.data.jdbc.core.dialect.JdbcPostgresDialect;

/**
 * Spring Boot normally resolves the dialect by opening a connection and reading the
 * database metadata. AOT processing runs at build time, where no database exists, so the
 * dialect is declared explicitly to keep repository code generation deterministic.
 */
@Configuration(proxyBeanMethods = false)
public class JdbcConfig {

    @Bean
    public JdbcDialect jdbcDialect() {
        return JdbcPostgresDialect.INSTANCE;
    }
}

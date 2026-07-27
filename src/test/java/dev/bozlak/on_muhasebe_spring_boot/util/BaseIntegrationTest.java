package dev.bozlak.on_muhasebe_spring_boot.util;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)   // For JWT Disabled
@ActiveProfiles("test")                     // application-test.properties
public abstract class BaseIntegrationTest {

    @ServiceConnection
    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:17-alpine");

    static {
        POSTGRE_SQL_CONTAINER.start();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanDatabaseBeforeEachTest(){
        List<String> tableNames = this.jdbcTemplate.queryForList("""
                SELECT quote_ident(table_name)
                FROM information_schema.tables
                WHERE table_schema = 'public' AND table_type = 'BASE TABLE'
                """, String.class);

        if (tableNames.isEmpty())
            return;

        this.jdbcTemplate.execute(
                "TRUNCATE TABLE " + String.join(", ", tableNames) + " RESTART IDENTITY CASCADE"
        );
    }
}

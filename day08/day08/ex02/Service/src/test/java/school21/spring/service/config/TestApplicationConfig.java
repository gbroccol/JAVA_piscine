package school21.spring.service.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class TestApplicationConfig {

    DataSource dataSource;

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
//                .addScript("data.sql")
                .build();
    }

    @Test
    public void getConnectionTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }
}
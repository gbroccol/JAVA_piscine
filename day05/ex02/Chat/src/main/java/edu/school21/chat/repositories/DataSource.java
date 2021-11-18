package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "gbroccol";
    private static final String password = "";

    public DataSource() {
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

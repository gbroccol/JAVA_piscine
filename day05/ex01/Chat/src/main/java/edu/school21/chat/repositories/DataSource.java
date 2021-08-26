package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class DataSource implements javax.sql.DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    public DataSource() {
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/postgres" );
        config.setUsername( "postgres" );
        config.setPassword( "password" );

        dataSource = new HikariDataSource(config);
//        try {
//            createTables();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

//    private void createTables() throws SQLException {
//        Connection connection = dataSource.getConnection();
//        String data = null;
//        String schema = null;
//        try {
//            data = new String(Files.readAllBytes(Paths.get("./src/main/resources/data.sql")));
//            schema = new String(Files.readAllBytes(Paths.get("./src/main/resources/schema.sql")));
//        } catch ( IOException e) {
//            e.printStackTrace();
//        }
//
//        Statement statement = null;
//        try {
//            statement = connection.createStatement();
//            statement.executeUpdate(schema);
////            statement.executeUpdate(data);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return statement;
//    }



//    +
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}

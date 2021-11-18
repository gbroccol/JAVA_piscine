package edu.school21.chat.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "gbroccol";
    private static final String password = "";

    private static final String schema = "./src/main/resources/schema.sql";
    private static final String data = "./src/main/resources/data.sql";

    public Connection getDBConnection() {
        Connection dbConnection = null;

        try {
            dbConnection = DriverManager.getConnection(url, username, password);
            return dbConnection;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return dbConnection;
    }

    public void executeUpdate(Connection connection, String file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);

            Scanner sc = new Scanner(inputStream).useDelimiter(";");

            Statement statement = connection.createStatement();

            while (sc.hasNext()) {
                statement.executeUpdate(sc.next().trim());
            }

        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) {
        Main chat = new Main();

        Connection connection = chat.getDBConnection();

        chat.executeUpdate(connection, schema);
        chat.executeUpdate(connection, data);
    }
}



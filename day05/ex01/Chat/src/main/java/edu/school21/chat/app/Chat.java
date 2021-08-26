package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;

public class Chat {

    private MessagesRepository messagesRepository;
    private Connection connection;

    public Chat () {
        try {
            DataSource dataSource = new DataSource();
            messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // INSERT, UPDATE, or DELETE statement or an SQL statement that returns nothing, such as an SQL DDL statement
    public void executeUpdate(String file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Scanner sc = new Scanner(inputStream).useDelimiter(";");
            Statement statement = connection.createStatement();
            while (sc.hasNext()) {
                statement.executeUpdate(sc.next().trim());
            }
            statement.close();
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findMessages() {
        Scanner sc = new Scanner(System.in);
        Optional<Message> message;
        long id;
        while (true)
        {
            System.out.print("Enter a message ID or exit\n-> ");
            if (sc.hasNextLong()) {
                id = sc.nextLong();
                if (id < 1) {
                    System.err.println("Incorrect id");
                    continue;
                } else if (id == 0) {
                    break;
                }
                message = messagesRepository.findById(id);
                try {
                    System.out.println(message.get());
                } catch (Exception e) {
                    System.err.println("Incorrect id");
                }
            } else if (sc.next().equals("exit")) {
                break;
            } else {
                System.out.println("ID must be number");
            }
        }
    }
}

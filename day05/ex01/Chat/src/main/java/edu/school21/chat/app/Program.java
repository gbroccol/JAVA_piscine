package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    private static final String schema = "./src/main/resources/schema.sql";
    private static final String data = "./src/main/resources/data.sql";

    private static MessagesRepository  messagesRepository;

    private static void executeUpdate(String file, DataSource dataSource) {
        try (Connection connectionDB = dataSource.getConnection();) {
            FileInputStream inputStream = new FileInputStream(file);
            Scanner sc = new Scanner(inputStream).useDelimiter(";");
            Statement statement = connectionDB.createStatement();

            while (sc.hasNext()) {
                statement.executeUpdate(sc.next().trim());
            }
            statement.close();
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runFindMessage() {
        Scanner scanner = new Scanner(System.in);
        Optional<Message> message;
        while (true) {
            System.out.print("Enter a message ID or exit\n -> ");
            if (scanner.hasNextLong()) {
                message = messagesRepository.findById(scanner.nextLong());

                if (message == null) {
                    System.out.println(ANSI_RED + "ERROR. Message not exists" + ANSI_RESET);
                } else {
                    System.out.println(message.get());
                }
            }
            else if (scanner.next().equals("exit")) {
                break;
            } else {
                System.out.println("ID must be a number");
            }
        }
    }

    public static void main(String[] args) {
            DataSource dataSource = new DataSource();
            messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            executeUpdate(schema, dataSource);
            executeUpdate(data, dataSource);
            runFindMessage();
    }
}

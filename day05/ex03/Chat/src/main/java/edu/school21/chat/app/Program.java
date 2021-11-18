package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.NotSavedSubEntityException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static final String schema = "./src/main/resources/schema.sql";
    private static final String data = "./src/main/resources/data.sql";

    private static MessagesRepository  messagesRepository;

    private static void executeUpdate(String file, DataSource dataSource) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Scanner sc = new Scanner(inputStream).useDelimiter(";");
            Statement statement = dataSource.getConnection().createStatement();

            while (sc.hasNext()) {
                statement.executeUpdate(sc.next().trim());
            }
            statement.close();
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testUpdate() {
        Long id = 4l;

        System.out.println(ANSI_BLUE + "BEFORE id = " + id + ANSI_RESET);
        System.out.println(messagesRepository.findById(id).get());

        Optional<Message> messageOptional = messagesRepository.findById(id);

        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();

            message.setText("UPDATE MESSAGE 'Hello' TO 'Bye'");
            message.setLocalDateTime(null);
            messagesRepository.update(message);
        }

        System.out.println(ANSI_BLUE + "AFTER" + ANSI_RESET);
        System.out.println(messagesRepository.findById(id).get());

        id = 6l;
        System.out.println(ANSI_BLUE + "BEFORE id = " + id + ANSI_RESET);
        try {
            System.out.println(messagesRepository.findById(id).get());
        } catch (NotSavedSubEntityException ex) {
            System.out.println(ex.getMessage());
        }

        if (messageOptional.isPresent()) {
            User author = new User(1l, "Nastya", "password");

            User chatOwner = author;

            Message message = new Message();

            message.setAuthor(author);
            message.setChatroom(new Chatroom(1l, "School21", chatOwner, null));
            message.setText("new message");
            message.setLocalDateTime(null);
            messagesRepository.update(message);
        }
        System.out.println(ANSI_BLUE + "AFTER" + ANSI_RESET);
        System.out.println(messagesRepository.findById(id).get());

        id = 7l;
        System.out.println(ANSI_BLUE + "BEFORE id = " + id + ANSI_RESET);
        try {
            System.out.println(messagesRepository.findById(id).get());
        } catch (NotSavedSubEntityException ex) {
            System.out.println(ex.getMessage());
        }

        if (messageOptional.isPresent()) {
            User author = new User(1l, "Nastya", "password");

            User chatOwner = author;

            Message message = new Message();

            message.setAuthor(author);
            message.setChatroom(new Chatroom(1l, "School21", chatOwner, null));
            message.setText("hi everyone");
            message.setLocalDateTime(LocalDateTime.now());
            messagesRepository.update(message);
        }
        System.out.println(ANSI_BLUE + "AFTER" + ANSI_RESET);
        System.out.println(messagesRepository.findById(id).get());

        System.out.println("----------------- CHANGE AUTHOR OF MESSAGE -------------------");
        id = 1l;
        System.out.println(ANSI_BLUE + "BEFORE id = " + id + ANSI_RESET);
        System.out.println(messagesRepository.findById(id).get());

        messageOptional = messagesRepository.findById(id);

        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();

            message.setAuthor(new User(4l, "Katya", "password", null, null));
            messagesRepository.update(message);
        }

        System.out.println(ANSI_BLUE + "AFTER" + ANSI_RESET);
        System.out.println(messagesRepository.findById(id).get());
    }

    public static void main(String args[]) {
            DataSource dataSource = new DataSource();
            messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            executeUpdate(schema, dataSource);
            executeUpdate(data, dataSource);
            testUpdate();
    }
}

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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    private static final String schema = "./src/main/resources/schema.sql";
    private static final String data = "./src/main/resources/data.sql";

    private static MessagesRepository  messagesRepository;

    private static void executeUpdate(String file, DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
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

    private static void testAddMessage() {

        List <Chatroom> listChat = new ArrayList<>();

        System.out.println("--------- OK ----------");
        User creator = new User(2l,"Lena", "password", null, listChat);
        User author = creator;
        Chatroom chatroom = new Chatroom(2l, "My Lovely Cats", creator, null);
        Message message = new Message(author, chatroom, "Timosha is my lovely cat!", LocalDateTime.now());
        messagesRepository.save(message);
        System.out.println("New message id is " + message.getMessageId());
        System.out.println(messagesRepository.findById(message.getMessageId()).get() + "\n");

        System.out.println("--------- AUTHOR NOT EXISTS ----------");
        try {
            creator = new User(2l,"Lena", "password", null, null);
            author = new User(42l,"Lena", "password", null, null);
            chatroom = new Chatroom(2l, "My Lovely Cats", creator, null);
            message = new Message(author, chatroom, "Timosha is my lovely cat!", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println("New message id is " + message.getMessageId());
        } catch (NotSavedSubEntityException ex) {
            System.out.println(ANSI_RED + ex + ANSI_RESET + "\n");
        }

        System.out.println("--------- CHATROOM NOT EXISTS ----------");
        try {
            creator = new User(5l,"Lena", "password", null, null);
            author = creator;
            chatroom = new Chatroom(42l, "My Lovely Cats", creator, null);
            message = new Message(author, chatroom, "Hi peer!", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println("New message id is " + message.getMessageId());
            System.out.println(messagesRepository.findById(message.getMessageId()).get());
        } catch (NotSavedSubEntityException ex) {
            System.out.println(ANSI_RED + ex + ANSI_RESET + "\n");
        }

        System.out.println("--------- AUTHOR IS NULL ----------");
        try {
            creator = new User(5l,"Lena", "password", null, null);
            chatroom = new Chatroom(2l, "My Lovely Cats", creator, null);
            message = new Message(null, chatroom, "Hi peer!", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println("New message id is " + message.getMessageId());
            System.out.println(messagesRepository.findById(message.getMessageId()).get());
        } catch (NotSavedSubEntityException ex) {
            System.out.println(ANSI_RED + ex + ANSI_RESET + "\n");
        }

        System.out.println("--------- CHATROOM IS NULL ----------");
        try {
            author = new User(1l,"Lena", "password", null, null);
            message = new Message(author, null, "Hi peer!", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println("New message id is " + message.getMessageId());
            System.out.println(messagesRepository.findById(message.getMessageId()).get());
        } catch (NotSavedSubEntityException ex) {
            System.out.println(ANSI_RED + ex + ANSI_RESET + "\n");
        }

        System.out.println("--------- OK ----------");
        creator = new User(2l,"Lena", "password", null, null);
        author = creator;
        chatroom = new Chatroom(2l, "My Lovely Cats", creator, null);
        message = new Message(author, chatroom, "Hi peer", LocalDateTime.now());
        messagesRepository.save(message);
        System.out.println("New message id is " + message.getMessageId());
        System.out.println(messagesRepository.findById(message.getMessageId()).get() + "\n");
    }

    public static void main(String args[]) {
            DataSource dataSource = new DataSource();
            messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            executeUpdate(schema, dataSource);
            executeUpdate(data, dataSource);
            testAddMessage();
    }
}

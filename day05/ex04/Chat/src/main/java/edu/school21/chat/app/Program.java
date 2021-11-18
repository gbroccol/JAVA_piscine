package edu.school21.chat.app;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static final String schema = "./src/main/resources/schema.sql";
    private static final String data = "./src/main/resources/data.sql";

    private static MessagesRepository  messagesRepository;
    private static UsersRepository     usersRepository;

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

    private static void testUserFindAll(int page, int size) {

        System.out.println(ANSI_BLUE + "PAGE = " + page + " SIZE = " + size + ANSI_RESET);

        List<User> listUsers = usersRepository.findAll(page, size);

        for (int i = 0; i < listUsers.size(); i++) {
            System.out.println(listUsers.get(i));
        }
    }

    public static void main(String args[]) {
        DataSource dataSource = new DataSource();
        messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        usersRepository = new UsersRepositoryJdbcImpl(dataSource);

        executeUpdate(schema, dataSource);
        executeUpdate(data, dataSource);

        testUserFindAll(1, 4);
        testUserFindAll(1, 3);
        testUserFindAll(2, 4);
        testUserFindAll(1, 0);
        testUserFindAll(0, 4);
        testUserFindAll(50, 4);
        testUserFindAll(2, 15);
    }
}

package edu.school21.sockets.server;

import edu.school21.sockets.app.Main;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.services.MessagesService;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;

@Component
public class Server {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    @Autowired
    private UsersService usersService;

    @Autowired
    private MessagesService messagesService;

    private List <ClientCommunicate> thrList = new ArrayList<>();

    public void start() {

        int clientNumber = 1;

        try (ServerSocket serverSocket = new ServerSocket(Main.PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientCommunicate thread = new ClientCommunicate(socket, clientNumber);
                thread.start();
                System.out.println("New client --- " + clientNumber++ + " --- connect to server");
                thrList.add(thread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAll(String text, String userName) {
        try {
            messagesService.saveMessage(text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; thrList.size() > i; i++) {
            ClientCommunicate client = thrList.get(i);
            if (client.clientActive == true) {
                client.sendMessage(userName + ": " + text);
            }
        }
    }

    public void nullThread(ClientCommunicate client) {
        thrList.remove(client);
    }

    public class ClientCommunicate extends Thread {

        int clientNumber;

        private Socket socket;

        private Scanner scanner;

        private PrintWriter writer;

        private boolean clientActive = false;

        public ClientCommunicate(Socket socket, int clientNumber) throws IOException {
            this.socket = socket;
            this.clientNumber = clientNumber;
            scanner = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);
        }

        private void closeConnection() throws IOException {
            writer.println("exit");
            nullThread(this);
            System.out.println("Client moved");
            socket.close();
            scanner.close();
            writer.close();
            return;
        }

        public void sendMessage(String text) {
            writer.println(text);
        }

        private void signUp() {
            writer.println("Enter username:");
            if (scanner.hasNextLine()) {
                String name = scanner.nextLine();

                while (name.equals("")) {
                    name = scanner.nextLine();
                }

                if (name.equals("exit")) {
                    return;
                }

                writer.println("Enter password:");
                if (scanner.hasNextLine()) {
                    String password = scanner.nextLine();

                    while (password.equals("")) {
                        password = scanner.nextLine();
                    }

                    if (password.equals("exit")) {
                        return;
                    }

                    if (password.equals("")) {
                        writer.println(ANSI_RED + "ERROR. Password ca not be an empty line" + ANSI_RESET);
                        return;
                    }

                    try {
                        if (!usersService.signUp(name, password)) {
                            writer.println(ANSI_RED + "Username already exists. Choose another username or type exit" + ANSI_RESET);
                            signUp();
                            return;
                        }
                        System.out.println("New user " + name + " added");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    writer.println("Start messaging");
                    sendingMessages(name);
                    return;
                }
            }
        }

        private void signIn() {
            writer.println("Enter username:");
            if (scanner.hasNextLine()) {

                String name = scanner.nextLine();

                while (name.equals("")) {
                    name = scanner.nextLine();
                }

                if (name.equals("exit")) {
                    return;
                }

                writer.println("Enter password:");
                if (scanner.hasNextLine()) {

                    String password = scanner.nextLine();

                    while (password.equals("")) {
                        password = scanner.nextLine();
                    }

                    if (password.equals("exit")) {
                        return;
                    }
                    if (!usersService.passwordCorrect(name, password)) {
                        writer.println(ANSI_RED + "Username or password not correct! Try again or type exit" + ANSI_RESET);
                        signIn();
                        return;
                    }
                    System.out.println("User " + name + " sign in");
                    writer.println("Start messaging");
                    sendingMessages(name);
                    return;
                }
            }
        }

        private void sendingMessages(String userName) {
            String message;

            clientActive = true;

            while (scanner.hasNextLine()) {
                message = scanner.nextLine();

                while (message.equals("")) {
                    message = scanner.nextLine();
                }

                if (message.equals("exit")) {
                    return;
                }
                sendAll(message, userName);
            }
        }

        @Override
        public void run() {

            synchronized (this) {
                try {
                    writer.println(ANSI_YELLOW + "Hello from Server!\tWrite one of the commands:\t1. signUp\t2. signIn\t3. exit" + ANSI_RESET);

                    while (true) {
                        if (scanner.hasNextLine()) {

                            String command = scanner.nextLine();

                            if (command.equals("signUp")) {
                                signUp();
                                closeConnection();
                                return;
                            } else if (command.equals("signIn")) {
                                signIn();
                                closeConnection();
                                return;
                            } else if (command.equals("exit")) {
                                closeConnection();
                                return;
                            } else if (command.equals("")){
                                continue;
                            } else {
                                writer.println("Too difficult command for me, sorry :(");
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



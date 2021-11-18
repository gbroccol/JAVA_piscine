package edu.school21.sockets.server;

import edu.school21.sockets.app.Main;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

@Component ("server")
public class Server {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(Main.PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();

                Scanner scanner = new Scanner(socket.getInputStream());

                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                writer.println("Hello from Server!");

                while (true) {
                    if (scanner.hasNextLine()) {
                        if (scanner.nextLine().equals("signUp")) {
                            writer.println("Enter username:");
                            if (scanner.hasNextLine()) {
                                String name = scanner.nextLine();
                                writer.println("Enter password:");
                                if (scanner.hasNextLine()) {
                                    String password = scanner.nextLine();
                                    password = passwordEncoder.encode(password);
                                    try {
                                        usersService.signUp(name, password);
                                        System.out.println("New user " + name + " added");
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                    writer.println("Successful!");
                                    socket.close();
                                    scanner.close();
                                    writer.close();
                                    break;
                                }
                            }
                        } else {
                            writer.println("Too difficult command for me, sorry :(");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

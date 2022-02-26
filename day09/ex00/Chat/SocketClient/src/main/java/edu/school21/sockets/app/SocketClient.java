package edu.school21.sockets.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    public void start(String ip, int port) throws IOException {
        try (Socket socket = new Socket(ip, port)) {
            Scanner scanServer = new Scanner(socket.getInputStream());

            String  strServer;

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanClient = new Scanner(System.in);
            String  strClient;

            while (scanServer.hasNextLine()) {
                strServer = scanServer.nextLine();
                System.out.println(strServer);

                if (strServer.equals("Successful!")) {
                    break;
                }

                if (scanClient.hasNextLine()) {
                    strClient = scanClient.nextLine();
                    writer.println(strClient);
                }
            }
            scanServer.close();
            writer.close();
            scanClient.close();
        }
    }
}
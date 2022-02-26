package edu.school21.sockets.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    public void start(String ip, int port) throws IOException {
        try (Socket socket = new Socket(ip, port)) {

            ReadFromServer readFromServer = new ReadFromServer(socket);
            readFromServer.start();

            WriteToServer writeToServer = new WriteToServer(socket);
            writeToServer.start();

            try {
                readFromServer.join();
                writeToServer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class ReadFromServer extends Thread {

        Scanner scanServer;

        public ReadFromServer(Socket socket) {
            try {
                scanServer = new Scanner(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            String  strServer;

            while (true) {
                strServer = scanServer.nextLine();

                if (strServer.equals("exit")) {
                    break;
                }

                System.out.println(strServer);
            }
            scanServer.close();
        }
    }

    public class WriteToServer extends Thread {

        Scanner scanClient = new Scanner(System.in);
        String  strClient;
        PrintWriter writer;

        public WriteToServer(Socket socket) {
            try {
                writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {

            while (true) {
                strClient = scanClient.nextLine();
                writer.println(strClient);
                if (strClient.equals("exit")) {
                    break;
                }
            }
            writer.close();
            scanClient.close();
        }
    }
}
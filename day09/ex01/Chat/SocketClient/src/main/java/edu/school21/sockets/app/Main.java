package edu.school21.sockets.app;

import java.io.IOException;

public class Main {

    public static int PORT;

    private static boolean portExists(String[] args) {

        if (args.length != 1 || !args[0].startsWith("--server-port=")) {
            System.err.println("ERROR. Expected --server-port=8081");
            return false;
        }

        try {
            PORT = Integer.parseInt(args[0].substring(14));
        } catch (NumberFormatException e) {
            System.err.println("ERROR. Port must be a number");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        if (!portExists(args)) {
            System.exit(1);
        }

        SocketClient socketClient = new SocketClient();
        try {
            socketClient.start("127.0.0.1", PORT);
        } catch (IOException e) {
            System.out.println("ConnectException: Connection refused ");
        }
    }
}

package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static int PORT;

    private static boolean portExists(String[] args) {

        if (args.length != 1 || !args[0].startsWith("--port=")) {
            System.err.println("ERROR. Expected --port=8081");
            return false;
        }

        try {
            PORT = Integer.parseInt(args[0].substring(7));
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

        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);

        Server server = context.getBean("server", Server.class);

        server.start();
    }
}



















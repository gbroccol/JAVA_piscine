import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void launchLs(ProcessBuilder processBuilder) throws IOException {
        processBuilder.command("bash", "-c", "ls");

        Process process = processBuilder.start();

        BufferedReader inn = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String file;

        while((file = inn.readLine()) != null) {
            System.out.println(file + " " + getFileSize(processBuilder, file));
        }
    }

    public static String getFileSize(ProcessBuilder processBuilder, String fileName) throws IOException {
        processBuilder.command("bash", "-c", "du -sh " + fileName);

        Process process = processBuilder.start();

        BufferedReader inn = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String fullLine = inn.readLine();

        if (fullLine == null) {
            return "1.0 M";
        }

        String sizeStr = fullLine.replaceFirst("\t" + fileName, "");

        for (int i = sizeStr.length() - 1; i > 0; i--) {
            if (sizeStr.charAt(i) >= '0' && sizeStr.charAt(i) <= '9') {
                sizeStr = new StringBuilder(sizeStr).insert(sizeStr.length() - i + 1, " ").toString();
                break;
            }
        }
        return sizeStr;
    }

    public static void launchMv(ProcessBuilder processBuilder, Scanner in) throws IOException {
        String arg1 = in.next();

        String arg2 = in.next();

        processBuilder.command("bash", "-c", "mv " + arg1 + " " + arg2);
        processBuilder.start();
    }

    public static void launchCd(ProcessBuilder processBuilder, Scanner in) throws IOException {
        String arg = in.next();

        processBuilder.command("bash", "-c", "cd " + arg + " ; pwd" );

        Process process = processBuilder.start();

        BufferedReader inn = new BufferedReader(new InputStreamReader(process.getInputStream()));

        processBuilder.directory(new File(inn.readLine()));
    }

    public static String launchPwd(ProcessBuilder processBuilder) throws IOException {
        processBuilder.command("bash", "-c", "pwd" );

        Process process = processBuilder.start();

        BufferedReader inn = new BufferedReader(new InputStreamReader(process.getInputStream()));

        return inn.readLine();
    }

    public static void main(String args[]) throws IOException {
        if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
            System.err.println("ERROR. Flag --current-folder= is required");
            System.exit(-1);
        }

        File path = new File(args[0].substring(17));

        if (!path.exists()) {
            System.err.println("ERROR. Path not exists");
            System.exit(-1);
        } else if (!path.isDirectory()) {
            System.err.println("ERROR. Not a dirrectory");
            System.exit(-1);
        }

        ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c");

        processBuilder.directory(path);

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print(ANSI_RED + "➜ " + ANSI_BLUE + launchPwd(processBuilder) + " " + ANSI_RESET);

            String command = in.next();

            if (command.equals("ls")) {
                launchLs(processBuilder);
            } else if (command.equals("mv")) {
                launchMv(processBuilder, in);
            } else if (command.equals("cd")) {
                launchCd(processBuilder, in);
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("the command is not found");
            }
        }
    }
}

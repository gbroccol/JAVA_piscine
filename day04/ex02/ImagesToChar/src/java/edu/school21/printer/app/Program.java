package edu.school21.printer.app;

import edu.school21.printer.logic.*;
import java.io.*;
import com.beust.jcommander.*;

public class Program {

    @Parameters(separators = "=")
    public static class MainCLIParameters {

        @Parameter(names = "--white", required = true)
        public static String white;

        @Parameter(names = "--black", required = true)
        public static String black;
    }

    public static boolean colorExists(String color) {

        if (!color.equals("BLACK")
                && !color.equals("RED")
                && !color.equals("GREEN")
                && !color.equals("YELLOW")
                && !color.equals("BLUE")
                && !color.equals("MAGENTA")
                && !color.equals("CYAN")
                && !color.equals("WHITE")) {
            System.out.println("Color not exists. Available colors are BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        JCommander jCommander = new JCommander(new MainCLIParameters());

        try {
            jCommander.parse(args);
        } catch (ParameterException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        if (!colorExists(MainCLIParameters.white) || !colorExists(MainCLIParameters.black)) {
            return;
        }

        try {
            ConvertBmp handler = new ConvertBmp();
            handler.readBmpImage();
            handler.printImage(MainCLIParameters.white, MainCLIParameters.black);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package edu.school21.printer.app;

import edu.school21.printer.logic.*;
import java.io.*;
import com.beust.jcommander.*;
import java.util.ArrayList;
import java.util.List;

public class Program {

    // @Parameter private List<String> parameters = new ArrayList<>();
    @Parameters(separators = "=")
    public static class MainCLIParameters {

        @Parameter(names = "--white", required = true)
        public static String white;

        @Parameter(names = "--black", required = true)
        public static String black;
    }


    public static void main(String[] args) {

        JCommander jCommander = new JCommander(new MainCLIParameters());

        try {
            jCommander.parse(args);
        } catch (ParameterException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        System.out.println("fine" + MainCLIParameters.black + MainCLIParameters.white);

        try {
            ConvertBmp handler = new ConvertBmp();
            handler.readBmpImage();
            handler.printImage(MainCLIParameters.white, MainCLIParameters.black);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

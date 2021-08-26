package edu.school21.printer.app;

import edu.school21.printer.logic.*;
import java.io.*;

public class Program {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Error\nThree arguments required, e.g. --white=value --black=value --filePath=value");
            return ;
        }

        try {
            Parser prs = new Parser(args);
            char white = prs.getWhite();
            char black = prs.getBlack();
            String path = prs.getPath();

            ConvertBmp handler = new ConvertBmp();
            handler.readBmpImage(path);
            handler.printImage(white, black);

        } catch (Parser.IncorrectInputException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

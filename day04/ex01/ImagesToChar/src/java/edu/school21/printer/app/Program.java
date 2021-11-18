package edu.school21.printer.app;

import edu.school21.printer.logic.*;
import java.io.*;

public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println(ANSI_RED + "Error. Three arguments required, e.g. --white=. --black=0" + ANSI_RESET);
            return ;
        }

        Parser prs = new Parser(args);
        if (prs.getParserOk()) {
            try {
                ConvertBmp handler = new ConvertBmp();
                if (handler.readBmpImage(prs.getPath()) == 0) {
                    handler.printImage(prs.getWhite(), prs.getBlack());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

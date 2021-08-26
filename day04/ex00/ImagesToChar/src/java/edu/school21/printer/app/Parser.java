package edu.school21.printer.app;

import java.io.IOException;

public class Parser {

    public static final int ARGS_COUNT = 3;

    boolean iswhite = false;
    boolean isblack = false;

    char white = 0;
    char black = 0;
    String path;

    public Parser(String[] args) throws IncorrectInputException {

        for (int i = 0; i < ARGS_COUNT; i++) {

            if (args[i].startsWith("--white=") && args[i].length() == 9) {
                white = args[i].charAt(8);
                iswhite = true;
            } else if (args[i].startsWith("--black=") && args[i].length() == 9) {
                black = args[i].charAt(8);
                isblack = true;
            } else if (args[i].startsWith("--filePath=") && args[i].length() > 11) {
                path = args[i].substring(11);
            } else {
                throw new IncorrectInputException();
            }
        }
    }

    public char getWhite() {
        return white;
    }

    public char getBlack() {
        return black;
    }

    public String getPath() {
        return path;
    }

    class IncorrectInputException extends IOException {
        public IncorrectInputException() {
            System.err.println("Incorrect input");
        }
    }
}
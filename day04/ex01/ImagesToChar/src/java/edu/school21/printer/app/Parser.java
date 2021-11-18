package edu.school21.printer.app;

public class Parser {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final int ARGS_COUNT = 2;

    char white = 0;
    char black = 0;
    String path = "";

    Boolean isParserOk = true;

    public Parser(String[] args) {

        boolean isWhite = false;
        boolean isBlack = false;

        path = "./src/resources/image.bmp";

        for (int i = 0; i < ARGS_COUNT; i++) {

            if (args[i].startsWith("--white=") && args[i].length() == 9) {
                white = args[i].charAt(8);
                isWhite = true;
            } else if (args[i].startsWith("--black=") && args[i].length() == 9) {
                black = args[i].charAt(8);
                isBlack = true;
            } else {
                isParserOk = false;
            }
        }
        if (!isWhite || !isBlack || !isParserOk) {
            isParserOk = false;
            System.out.println(ANSI_RED + "Wrong arguments" + ANSI_RESET);
        } else {
            isParserOk = true;
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

    public Boolean getParserOk() {
        return isParserOk;
    }
}
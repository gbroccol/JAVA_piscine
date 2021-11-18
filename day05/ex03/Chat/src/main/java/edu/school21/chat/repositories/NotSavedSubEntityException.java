package edu.school21.chat.repositories;

public class NotSavedSubEntityException extends RuntimeException {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public NotSavedSubEntityException() {
        super(ANSI_RED + "id doesn't exist" + ANSI_RESET);
    }
}

package edu.school21.printer.logic.preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String action(String s) {
        return s.toUpperCase();
    }
}

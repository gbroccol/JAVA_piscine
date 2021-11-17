package edu.school21.printer.logic.printer;

import edu.school21.printer.logic.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    String prefix = "";
    Renderer renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String s) {
        if (prefix.equals("") | prefix == null)
            renderer.send(s);
        else
            renderer.send(prefix + " " + s);
    }
}

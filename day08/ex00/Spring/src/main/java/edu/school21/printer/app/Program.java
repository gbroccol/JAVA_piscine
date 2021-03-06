package edu.school21.printer.app;

import edu.school21.printer.logic.preprocessor.PreProcessor;
import edu.school21.printer.logic.preprocessor.PreProcessorToUpperImpl;
import edu.school21.printer.logic.printer.Printer;
import edu.school21.printer.logic.printer.PrinterWithPrefixImpl;
import edu.school21.printer.logic.renderer.Renderer;
import edu.school21.printer.logic.renderer.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        printer.print ("Hello!") ;
        context.close();
    }
}

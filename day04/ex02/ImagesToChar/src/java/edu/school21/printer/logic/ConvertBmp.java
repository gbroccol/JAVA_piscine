package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.*;
import com.diogonunes.jcolor.*;
import com.diogonunes.jcolor.Attribute;
import java.util.HashMap;

public class ConvertBmp {

    public static final int BLACK = Color.BLACK.getRGB();

    int[][] array2D = null;
    private AvailableColors availableColors;

    public void readBmpImage() throws IOException {

        BufferedImage image = ImageIO.read(new File("./src/resources/image.bmp"));

        int imgHeight = image.getHeight();
        int imgWidth = image.getWidth();

        this.array2D = new int[imgHeight][imgWidth];

        for (int x = 0; x < imgHeight; x++)
        {
            for (int y = 0; y < imgWidth; y++)
            {
                int color = image.getRGB(y, x);

                if (color == BLACK) {
                    this.array2D[x][y] = 1;
                } else {
                    this.array2D[x][y] = 0;
                }
            }
        }
    }

    public void printImage(String white, String black) {

        if (this.array2D == null) {
            System.err.println("Error\nRead image file first");
            return;
        }

        HashMap<String, Integer> colors = new HashMap<>();
        colors.put("BLACK", 0);
        colors.put("RED", 1);
        colors.put("GREEN", 2);
        colors.put("YELLOW", 3);
        colors.put("BLUE", 4);
        colors.put("MAGENTA", 5);
        colors.put("CYAN", 6);
        colors.put("WHITE", 7);

        try {
            colors.get(white);
            colors.get(black);
        } catch (NullPointerException e) {
            System.err.println("Only the next colors sipported: BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE");
            return;
        }

        int imgHeight = array2D.length;
        int imgWidth = array2D[0].length;

        for (int x = 0; x < imgHeight; x++) {
            for (int y = 0; y < imgWidth; y++) {
                if (array2D[x][y] == 0) {
                    System.out.print(Ansi.colorize(" ", Attribute.BACK_COLOR(colors.get(white))));
                } else {
                    System.out.print(Ansi.colorize(" ", Attribute.BACK_COLOR(colors.get(black))));
                }
            }
            System.out.println();
        }
    }
}
package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ConvertBmp {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final int BLACK = Color.BLACK.getRGB();

    int[][] array2D = null;

    public int readBmpImage(String BmpFile) throws IOException {

        File file = new File(BmpFile);
        if (file.isDirectory() || !file.canRead() || !file.isFile()) {
            System.err.println(ANSI_RED + "Wrong file path" + ANSI_RESET);
            return 1;
        }

        BufferedImage image = ImageIO.read(file);

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
        return 0;
    }

    public void printImage(char white, char black) {

        if (this.array2D == null) {
            System.err.println(ANSI_RED + "Error\nRead image file first" + ANSI_RESET);
            return;
        }

        int imgHeight = array2D.length;
        int imgWidth = array2D[0].length;

        for (int x = 0; x < imgHeight; x++) {
            for (int y = 0; y < imgWidth; y++) {
                if (array2D[x][y] == 0) {
                    System.out.print(white);
                } else {
                    System.out.print(black);
                }
            }
            System.out.println();
        }
    }
}
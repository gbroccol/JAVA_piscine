package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ConvertBmp {

    public static final int BLACK = Color.BLACK.getRGB();

    int[][] array2D = null;

    public void readBmpImage(String BmpFile) throws IOException {

        BufferedImage image = ImageIO.read(new File(BmpFile));

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

    public void printImage(char white, char black) {

        if (this.array2D == null) {
            System.err.println("Error\nRead image file first");
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
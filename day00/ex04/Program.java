import java.util.Scanner;

public class Program {
    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;
    private static final int SPACE_BETWEEN_ROWS = 2;
    private static int minChar = 65535;
    private static int maxChar = 0;
    private static int maxNmbOfOccurrences = 0;

    public Program() {
    }

    private static int amountSymbols(int var0) {
        int var1;
        for(var1 = 0; var0 > 0; ++var1) {
            var0 /= 10;
        }

        return var1;
    }

    private static int[] strToBufUnicode(String var0) {
        char[] var1 = var0.toCharArray();
        int[] var2 = new int['\uffff'];
        char[] var3 = var1;
        int var4 = var1.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            char var6 = var3[var5];
            if (var6 > maxChar) {
                maxChar = var6;
            }

            if (var6 < minChar) {
                minChar = var6;
            }

            int var10002 = var2[var6]++;
            if (maxNmbOfOccurrences < var2[var6]) {
                maxNmbOfOccurrences = var2[var6];
            }
        }

        return var2;
    }

    private static void printTwoDimensionalArray(char[][] var0) {
        for(int var1 = 0; var1 < var0.length; ++var1) {
            for(int var2 = 0; var2 < var0[0].length; ++var2) {
                System.out.print(var0[var1][var2]);
            }

            System.out.println();
        }

    }

    private static void addBarAndNmb(char[][] var0, int var1, int var2, int var3) {
        int var4 = 10;
        if (var1 != maxNmbOfOccurrences) {
            var4 = 10 * var1 / maxNmbOfOccurrences;
        }

        char[] var5 = ("" + var1).toCharArray();

        int var6;
        for(var6 = 0; var6 < var5.length; ++var6) {
            var0[10 - var4][var3 + var6 + var2 - var5.length] = var5[var6];
        }

        for(var6 = 10; var4 > 0; --var6) {
            var0[var6][var3 + var2 - 1] = '#';
            --var4;
        }

    }

    private static void drawBarGraph(char[][] var0, int var1, int[] var2) {
        for(int var3 = 0; var3 < 10 * var1; var3 += var1) {
            int var4 = 0;
            int var5 = 0;

            for(int var6 = minChar; var6 <= maxChar; ++var6) {
                if (var4 < var2[var6]) {
                    var4 = var2[var6];
                    var5 = var6;
                }
            }

            if (var4 == 0) {
                break;
            }

            var2[var5] = 0;
            addBarAndNmb(var0, var4, var1, var3);
            var0[11][var3 + var1 - 1] = (char)var5;
        }

    }

    public static void main(String[] var0) {
        Scanner var1 = new Scanner(System.in);
        String var2 = var1.nextLine();
        if (var2.length() > 999) {
            System.err.println("IllegalLength");
            System.exit(-1);
        } else if (var2.length() == 0) {
            System.err.println("String is empty");
            return;
        }

        int[] var3 = strToBufUnicode(var2);
        int var4 = amountSymbols(maxNmbOfOccurrences) + 2;
        char[][] var5 = new char[12][10 * var4];

        for(int var6 = 0; var6 < 12; ++var6) {
            for(int var7 = 0; var7 < 10 * var4; ++var7) {
                var5[var6][var7] = ' ';
            }
        }

        drawBarGraph(var5, var4, var3);
        printTwoDimensionalArray(var5);
    }
}

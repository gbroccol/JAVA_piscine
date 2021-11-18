import java.util.Scanner;

public class Program {
    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;
    private static final int SPACE_BETWEEN_ROWS = 2;
    private static int minChar = 65535;
    private static int maxChar = 0;
    private static int maxNmbOfOccurrences = 0;
    private static final int MAX_INT_LENGTH = 999;

    private static void printTwoDimensionalArray(char[][] arr) {
        System.out.print("\n");
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                System.out.print(arr[i][j]);
            }

            System.out.println();
        }
    }

    private static int amountSymbols(int nmb) {
        int res;

        for (res = 0; nmb > 0; ++res) {
            nmb /= 10;
        }

        return res;
    }

    private static int[] strToBufUnicode(String str) {
        char[] charArray = str.toCharArray();

        int[] intArray = new int[MAX_INT_LENGTH];

        for (int i = 0; i < charArray.length; i++) {
            char smb = charArray[i];

            if (smb > maxChar) {
                maxChar = smb;
            }

            if (smb < minChar) {
                minChar = smb;
            }

            intArray[smb]++;

            if (maxNmbOfOccurrences < intArray[smb]) {
                maxNmbOfOccurrences = intArray[smb];
            }
        }

        return intArray;
    }

    private static void addBarAndNmb(char[][] barGraph, int lettersAmount, int columnWidth, int column) {
        int barHight = HEIGHT;

        if (lettersAmount != maxNmbOfOccurrences) {
            barHight = HEIGHT * lettersAmount / maxNmbOfOccurrences;
        }

        char[] nmbAsChar = ("" + lettersAmount).toCharArray();

        int i;

        for (i = 0; i < nmbAsChar.length; ++i) {
            barGraph[HEIGHT - barHight][column + i + columnWidth - nmbAsChar.length] = nmbAsChar[i];
        }

        for (i = HEIGHT; barHight > 0; --i) {
            barGraph[i][column + columnWidth - 1] = '#';
            --barHight;
        }
    }

    private static void drawBarGraph(char[][] barGraph, int columnWidth, int[] buf) {
        for (int column = 0; column < WIDTH * columnWidth; column += columnWidth) {

            int lettersAmount = 0;

            int symbol = 0;

            for (int j = minChar; j <= maxChar; j++) {
                if (lettersAmount < buf[j]) {
                    lettersAmount = buf[j];
                    symbol = j;
                }
            }

            if (lettersAmount == 0) {
                break;
            }

            buf[symbol] = 0;
            addBarAndNmb(barGraph, lettersAmount, columnWidth, column);
            barGraph[HEIGHT + 1][column + columnWidth - 1] = (char)symbol;
        }
    }

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        if (str.length() > MAX_INT_LENGTH) {
            System.err.println("IllegalLength");
            System.exit(-1);
        } else if (str.length() == 0) {
            System.err.println("String is empty");
            return;
        }

        int[] buf = strToBufUnicode(str);

        int columnWidth = amountSymbols(maxNmbOfOccurrences) + SPACE_BETWEEN_ROWS;

        char[][] barGraph = new char[HEIGHT + 2][WIDTH * columnWidth];

        for (int j = 0; j < (HEIGHT + 2); ++j) {
            for (int i = 0; i < WIDTH * columnWidth; ++i) {
                barGraph[j][i] = ' ';
            }
        }

        drawBarGraph(barGraph, columnWidth, buf);
        printTwoDimensionalArray(barGraph);
    }
}

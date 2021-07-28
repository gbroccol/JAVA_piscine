import java.util.Scanner;

public class Program {

    public static final int MAX_WEEKS = 18;

    private static void error() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private static int getNextInt(Scanner sc) {
        if (sc.hasNextInt() == false) {
            error();
        }

        return sc.nextInt();
    }

    private static int getEval(Scanner sc) {
        int minEval = 10;

        int tmp = 0;

        for (int i = 0; i < 5; i++) {
            tmp = getNextInt(sc);
            if (tmp > 9) {
                error();
            }

            if (tmp < minEval) {
                minEval = tmp;
            }
        }

        if (minEval < 1) {
            error();
        }

        return minEval;
    }

    private static long reverse(long nmb) {
        long res = 0;

        while (nmb > 0) {
            res = res * 10 + nmb % 10;
            nmb = nmb / 10;
        }

        return res;
    }

    private static void printStatistics(long allEval) {
        long minEval = 0;

        for (int i = 1; allEval > 0; i++) {
            System.out.print("Week " + i + " ");
            minEval = allEval % 10;
            allEval = allEval / 10;
            for (int j = 0; j < minEval; j++) {
                System.out.print("=");
            }

            System.out.println(">");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long allEval = 0;

        int weekNmb = 0;

        String weekName = sc.next();

        int tmp = 0;

        while (weekName.equals("42") == false) {
            if (weekName.equals("Week") == false) {
                error();
            }

            weekNmb = getNextInt(sc);
            tmp++;
            if (tmp != weekNmb || tmp > MAX_WEEKS) {
                error();
            }

            allEval = allEval * 10 + getEval(sc);
            weekName = sc.next();
        }

        if (allEval != 0) {
            printStatistics(reverse(allEval));
        }

        sc.close();
    }
}

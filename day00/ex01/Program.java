
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        int iter = 1;

        Scanner sc = new Scanner(System.in);

        System.out.print("");
        if (!sc.hasNextInt()) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        int number = sc.nextInt();

        if (number < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        else if (number == 2) {
            System.out.print("true ");
            System.out.println(iter);
        }
        else {
            for (int i = 2; i * i <= number; i++, iter++) {
                if (number % i == 0) {
                    System.out.print("false ");
                    System.out.println(iter);
                    System.exit(0);
                }
            }

            System.out.print("true ");
            System.out.println(iter);
        }
        
        sc.close();
    }
}

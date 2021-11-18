import java.util.Scanner;

public class Program {

    private static int sumDigits(int nmb) {
        int result = 0;

        while (nmb > 0) {
            result = result + nmb % 10;
            nmb = nmb / 10;
        }
        
        return result;
    }

    private static boolean isPrime(int number) {

        if (number == 2) {
            return true;
        }
        else {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }

            }

            return true;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int sum = 0;

        int nmb = 0;

        while (true) {
            nmb = sc.nextInt();
            if (nmb == 42) {
                break;
            }
            
            if (isPrime(sumDigits(nmb))) {
                sum++;
            }
        }

        System.out.print("Count of coffee-request - ");
        System.out.println(sum);
        sc.close();
    }
}

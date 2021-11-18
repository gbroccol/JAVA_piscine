package edu.school21.numbers;

public class NumberWorker {

    public int digitsSum(int number) {
        int result = 0;

        int minus = 1;

        if (number < 0) {
            number *= -1;
            minus = -1;
        }

        while (number > 0) {
            result = result + number % 10;
            number = number / 10;
        }
        result *= minus;
        return result;
    }

    public boolean isPrime(int number) throws IllegalNumberException {
        if (number == 2) {
            return true;
        } else if (number <= 1) {
            throw new IllegalNumberException();
        } else {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public class IllegalNumberException extends RuntimeException {
        public IllegalNumberException() {
            super("ERROR\nWrite number more than 1");
        }
    }
}

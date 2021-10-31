package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 199, 151})
    public void isPrimeForPrimes(int candidate) {
        assertTrue(NumberWorker.isPrime(candidate));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30})
    void isPrimeForNotPrimes(int candidate) {
        assertFalse(NumberWorker.isPrime(candidate));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2, -3, -4, -5})
    void isPrimeForIncorrectNumbers(int candidate) {
        assertThrows(NumberWorker.IllegalNumberException.class,()->{NumberWorker.isPrime(candidate);});
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void checkDigitsSum(int num, int sum) {
        assertEquals(NumberWorker.digitsSum(num), sum);
    }
}
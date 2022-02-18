package ru.nsu.voronova;

import java.util.concurrent.ExecutionException;

public class PrimeSearch {
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        } else if (number <= 3) {
            return true;
        } else if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0)
                return false;
        }
        return true;
    }

    public boolean hasPrime(int[] array) throws ExecutionException, InterruptedException, NullPointerException {
        if (array == null) {
            throw new NullPointerException();
        }
        for (int number : array) {
            if (isPrime(number))
                return true;
        }
        return false;
    }
}

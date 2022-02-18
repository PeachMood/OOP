package ru.nsu.voronova.unitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.voronova.PrimeSearch;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class PrimeSearchTest {
    @Test
    public void isPrime_negativeNumber() {
        Assertions.assertFalse(new PrimeSearch().isPrime(-10));
    }

    @Test
    public void isPrime_smallNumber() {
        assertTrue(new PrimeSearch().isPrime(3));
    }

    @Test
    public void isPrime_evenNumber() {
        assertFalse(new PrimeSearch().isPrime(200));
    }

    @Test
    public void isPrime_largePrimeNumber() {
        assertTrue(new PrimeSearch().isPrime(1073676287));
    }

    @Test
    public void isPrime_largeNotPrimeNumber() {
        assertFalse(new PrimeSearch().isPrime(16217903));
    }

    @Test
    public void hasPrime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PrimeSearch().hasPrime(null));
    }

    @Test
    public void hasPrime_smallData1() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 300);
        assertFalse(new PrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_smallData2() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 2, 150);
        array[size - 1] = 13;
        assertTrue(new PrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_largeData1() throws ExecutionException, InterruptedException {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 182638612);
        assertFalse(new PrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_largeData2() throws ExecutionException, InterruptedException {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 2, 89127914);
        array[size - 1] = 7;
        assertTrue(new PrimeSearch().hasPrime(array));
    }
}
package ru.nsu.voronova.unitTest;

import org.junit.jupiter.api.Test;
import ru.nsu.voronova.ThreadPrimeSearch;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ThreadPrimeSearchTest {
    @Test
    public void hasPrime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ThreadPrimeSearch().hasPrime(null));
    }

    @Test
    public void hasPrime_smallData1() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 169);
        assertFalse(new ThreadPrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_smallData2() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 2, 169);
        array[size - 1] = 17;
        assertTrue(new ThreadPrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_largeData1() throws ExecutionException, InterruptedException {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 1826381768);
        assertFalse(new ThreadPrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_largeData2() throws ExecutionException, InterruptedException {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 2, 1826381768);
        array[size - 1] = 19;
        assertTrue(new ThreadPrimeSearch().hasPrime(array));
    }
}
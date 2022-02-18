package ru.nsu.voronova.unitTest;

import org.junit.jupiter.api.Test;
import ru.nsu.voronova.StreamPrimeSearch;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class StreamPrimeSearchTest {
    @Test
    public void hasPrime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StreamPrimeSearch().hasPrime(null));
    }

    @Test
    public void hasPrime_smallData1() {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 121);
        assertFalse(new StreamPrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_smallData2() {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 2, 256);
        array[size - 1] = 17;
        assertTrue(new StreamPrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_largeData1() {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 1928377914);
        assertFalse(new StreamPrimeSearch().hasPrime(array));
    }

    @Test
    public void hasPrime_largeData2() {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 2, 1928377914);
        array[size - 1] = 3;
        assertTrue(new StreamPrimeSearch().hasPrime(array));
    }
}
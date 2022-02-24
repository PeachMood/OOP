package ru.nsu.voronova.unitTest;

import org.junit.jupiter.api.Test;
import ru.nsu.voronova.StreamNotPrimeSearch;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StreamNotPrimeSearchTest {
    @Test
    public void hasPrime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StreamNotPrimeSearch().search(null));
    }

    @Test
    public void search_smallData1() {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 19);
        assertFalse(new StreamNotPrimeSearch().search(array));
    }

    @Test
    public void search_smallData2() {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 11);
        array[size - 1] = 424;
        assertTrue(new StreamNotPrimeSearch().search(array));
    }

    @Test
    public void search_largeData1() {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 1046527);
        assertFalse(new StreamNotPrimeSearch().search(array));
    }

    @Test
    public void search_largeData2() {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 90825);
        array[size - 1] = 66;
        assertTrue(new StreamNotPrimeSearch().search(array));
    }
}
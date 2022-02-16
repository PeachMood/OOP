package ru.nsu.voronova;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamPrimeSearch extends PrimeSearch {
    @Override
    public boolean hasPrime(int[] array) {
        IntStream intStream = Arrays.stream(array);
        return intStream.parallel().anyMatch(this::isPrime);
    }
}

package ru.nsu.voronova.performanceTest;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.Test;
import ru.nsu.voronova.PrimeSearch;
import ru.nsu.voronova.StreamPrimeSearch;
import ru.nsu.voronova.ThreadPrimeSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PerformanceTest {
    private int[] createArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        int randomIndex = (int) (Math.random() * size);
        int[] array = new int[size];
        Arrays.fill(array, 0, randomIndex, 20);
        array[randomIndex] = 7;
        if (randomIndex < size - 1) {
            Arrays.fill(array, randomIndex + 1, size, 20);
        }
        return array;
    }

    private long singleTest(PrimeSearch primeSearch, int[] array) throws ExecutionException, InterruptedException {
        List<Long> results = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            long time = System.nanoTime();
            primeSearch.hasPrime(array);
            time = System.nanoTime() - time;
            results.add(time);
        }
        return Collections.min(results);
    }

    @Test
    public void performanceTest() throws ExecutionException, InterruptedException, IOException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("PrimeSearch");
        XYSeries series2 = new XYSeries("ThreadPrimeSearch");
        XYSeries series3 = new XYSeries("StreamPrimeSearch");

        for (int size = 10; size <= 1000000; size *= 10) {
            int[] array = createArray(size);
            long time = singleTest(new PrimeSearch(), array);
            series1.add(size, time);
            time = singleTest(new ThreadPrimeSearch(), array);
            series2.add(size, time);
            time = singleTest(new StreamPrimeSearch(), array);
            series3.add(size, time);
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        LineChart chart = new LineChart(dataset);
        chart.createFile();
    }
}

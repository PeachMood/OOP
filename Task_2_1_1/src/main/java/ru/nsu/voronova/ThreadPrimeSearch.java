package ru.nsu.voronova;

import java.util.*;
import java.util.concurrent.*;

public class ThreadPrimeSearch extends PrimeSearch {
    private final int THREADS_NUMBER = 10;

    @Override
    public boolean hasPrime(int[] array) throws ExecutionException, InterruptedException, NullPointerException {
        if (array == null) {
            throw new NullPointerException();
        }
        List<Integer> numberList = Arrays.stream(array).boxed().toList();
        Deque<Integer> deque = new ArrayDeque<>(numberList);

        Callable<Boolean> task = () -> {
            Integer number;
            while ((number = deque.poll()) != null) {
                if (isPrime(number)) {
                    return true;
                }
            }
            return false;
        };

        ExecutorService pool = Executors.newFixedThreadPool(THREADS_NUMBER);
        List<Future<Boolean>> futureList = new ArrayList<>();
        for (int i = 0; i < THREADS_NUMBER; ++i) {
            Future<Boolean> future = pool.submit(task);
            futureList.add(future);
        }
        for (Future<Boolean> future : futureList) {
            if (future.get()) {
                pool.shutdown();
                return true;
            }
        }
        pool.shutdown();
        return false;
    }
}

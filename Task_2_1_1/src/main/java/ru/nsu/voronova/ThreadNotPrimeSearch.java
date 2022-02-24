package ru.nsu.voronova;

import java.util.*;
import java.util.concurrent.*;

public class ThreadNotPrimeSearch extends NotPrimeSearch {
    private Deque<Integer> deque;

    private synchronized Integer getNumber() {
        if (deque.isEmpty()) {
            return null;
        }
        return deque.pop();
    }

    @Override
    public boolean search(int[] array) throws ExecutionException, InterruptedException, NullPointerException {
        if (array == null) {
            throw new NullPointerException();
        }
        final int threadsNumber = Runtime.getRuntime().availableProcessors();
        List<Integer> numberList = Arrays.stream(array).boxed().toList();
        deque = new ArrayDeque<>(numberList);

        Callable<Boolean> task = () -> {
            Integer number;
            while ((number = getNumber()) != null) {
                if (!isPrime(number)) {
                    return true;
                }
            }
            return false;
        };
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int i = 0; i < threadsNumber; ++i) {
            tasks.add(task);
        }

        ExecutorService pool = Executors.newFixedThreadPool(threadsNumber);
        List<Future<Boolean>> futureList = pool.invokeAll(tasks);
        for (Future<Boolean> future : futureList) {
            if (future.get()) {
                pool.shutdownNow();
                return true;
            }
        }
        pool.shutdownNow();
        return false;
    }
}

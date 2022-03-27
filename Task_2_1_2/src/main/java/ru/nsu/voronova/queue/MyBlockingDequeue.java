package ru.nsu.voronova.queue;

import java.util.*;

public class MyBlockingDequeue<T> {
    private final int size;
    private final Deque<T> dequeue;

    public MyBlockingDequeue(int size) {
        this.size = size;
        this.dequeue = new ArrayDeque<>();
    }

    public synchronized T get() throws InterruptedException {
        while (dequeue.isEmpty()) {
            wait();
        }
        T object = dequeue.pop();
        notifyAll();
        return object;
    }

    public synchronized List<T> get(int amount) throws IllegalArgumentException, InterruptedException {
        if (amount < 1 || amount > size) {
            throw new IllegalArgumentException();
        }
        while (dequeue.isEmpty()) {
            wait();
        }
        List<T> objects = new ArrayList<>();
        while (!dequeue.isEmpty() && objects.size() != amount) {
            objects.add(dequeue.pop());
        }
        return objects;
    }

    public synchronized void put(T object) throws NullPointerException, InterruptedException {
        if (object == null) {
            throw new NullPointerException();
        }
        while (dequeue.size() == size) {
            wait();
        }
        dequeue.push(object);
        notifyAll();
    }
}

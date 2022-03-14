package ru.nsu.voronova;

import java.util.*;

public class QueueBlocking<T> {
    private final int size;
    private final Deque<T> dequeue;

    public QueueBlocking(int size) {
        this.size = size;
        this.dequeue = new ArrayDeque<>();
    }

    public synchronized T get() throws InterruptedException {
        while (dequeue.size() < 1) {
            wait();
        }
        T object = dequeue.pop();
        notifyAll();
        return object;
    }

    public List<T> get(int amount) throws IllegalArgumentException, InterruptedException {
        if (amount < 1 || amount > size) {
            throw new IllegalArgumentException();
        }
        List<T> objects = new ArrayList<>();
        for (int i = 0; i < amount; ++i) {
            objects.add(get());
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

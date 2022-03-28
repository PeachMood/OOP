package ru.nsu.voronova.queue;

import java.util.*;

/**
 * This class provides methods for working with a shared dequeue.
 *
 * @param <T> - The type of data stored in a shared dequeue.
 */
public class MyBlockingDequeue<T> {
    private final int size;
    private final Deque<T> dequeue;

    /**
     * Creates an instance of the class MyBlockingDequeue.
     *
     * @param size maximal size of the dequeue.
     */
    public MyBlockingDequeue(int size) {
        this.size = size;
        this.dequeue = new ArrayDeque<>();
    }

    /**
     * Shows if the dequeue is empty
     *
     * @return true if dequeue is empty.
     */
    public synchronized boolean isEmpty() {
        return dequeue.size() == 0;
    }

    /**
     * Returns the number of objects in the dequeue.
     *
     * @return number of objects in the dequeue.
     */
    public synchronized int getSize() {
        return dequeue.size();
    }

    /**
     * Retrieves an object from the dequeue and deletes it
     *
     * @return object from dequeue.
     * @throws InterruptedException in case of an error.
     */
    public synchronized T get() throws InterruptedException {
        while (dequeue.isEmpty()) {
            wait();
        }
        T object = dequeue.pop();
        notifyAll();
        return object;
    }

    /**
     * Retrieves several objects from the dequeue and deletes them.
     *
     * @param amount - amount of objects.
     * @return - objects from dequeue.
     * @throws IllegalArgumentException if the amount argument is not positive or exceeds the maximum dequeue size.
     * @throws InterruptedException     in case of an exception.
     */
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

    /**
     * Puts an object in a dequeue
     *
     * @param object - the object to be added to the dequeue.
     * @throws NullPointerException if the object is null.
     * @throws InterruptedException in case of an exception.
     */
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

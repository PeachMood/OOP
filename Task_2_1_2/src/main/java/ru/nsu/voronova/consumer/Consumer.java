package ru.nsu.voronova.consumer;

/**
 * The Consumer interface must be implemented by any class whose instances are intended to receive objects from some data array.
 *
 * @param <T> - type of consumed objects.
 */
public interface Consumer<T> {
    /**
     * The implementation of this method must allow the class to consume an object from some shared resource.
     *
     * @return - consumed object.
     */
    T consume();
}

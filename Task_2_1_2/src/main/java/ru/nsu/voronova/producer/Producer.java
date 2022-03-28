package ru.nsu.voronova.producer;

/**
 * The Producer interface must be implemented by any class intended to add new objects to a shared resource.
 *
 * @param <T> - type of produced objects.
 */
public interface Producer<T> {

    /**
     * The implementation of this method must allow the class to add new objects to the shared resource.
     *
     * @param object - object which should be produced.
     */
    void produce(T object);
}

package ru.nsu.voronova.producer;

public interface Producer<T> {
    void produce(T object);
}

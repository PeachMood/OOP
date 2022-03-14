package ru.nsu.voronova;

import java.util.List;

public class Courier extends Consumer {
    private final int bagCapacity;
    private final QueueBlocking<Order> storage;
    private final long MAX_DELIVERY_TIME = 100;

    public Courier(int bagCapacity, QueueBlocking<Order> storage) {
        this.bagCapacity = bagCapacity;
        this.storage = storage;
    }

    @Override
    public void consume() throws InterruptedException {
        List<Order> orders = storage.get(bagCapacity);
        for (Order order : orders) {
            Thread.sleep(MAX_DELIVERY_TIME);
        }
    }
}

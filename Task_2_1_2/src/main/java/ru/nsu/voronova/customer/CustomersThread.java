package ru.nsu.voronova.customer;

import ru.nsu.voronova.queue.SharedQueue;
import ru.nsu.voronova.order.Order;

public class CustomersThread implements Runnable {
    private boolean isRunning;
    private final SharedQueue<Order> orders;

    public CustomersThread(SharedQueue<Order> orders) {
        this.isRunning = false;
        this.orders = orders;
    }

    @Override
    public void run() {
        isRunning = true;
        for (int i = 0; this.isRunning; ++i) {
            Order order = new Order(i);
            new Customer(this.orders).produce(order);
        }
    }

    public void stop() {
        isRunning = false;
    }
}

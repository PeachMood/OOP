package ru.nsu.voronova.customer;

import ru.nsu.voronova.queue.MyBlockingDequeue;
import ru.nsu.voronova.order.Order;

public class Customers implements Runnable {
    private boolean isRunning;
    private final MyBlockingDequeue<Order> queue;

    public Customers(MyBlockingDequeue<Order> queue) {
        this.isRunning = false;
        this.queue = queue;
    }

    @Override
    public void run() {
        isRunning = true;
        for (int i = 0; this.isRunning; ++i) {
            Order order = new Order(i);
            new Customer(this.queue).produce(order);
        }
    }

    public void stop() {
        isRunning = false;
    }
}

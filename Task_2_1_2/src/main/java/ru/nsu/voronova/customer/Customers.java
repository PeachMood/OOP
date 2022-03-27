package ru.nsu.voronova.customer;

import ru.nsu.voronova.queue.MyBlockingDequeue;
import ru.nsu.voronova.order.Order;

public class Customers implements Runnable {
    private boolean runCustomers;
    private final MyBlockingDequeue<Order> queue;

    public Customers(MyBlockingDequeue<Order> queue) {
        this.runCustomers = false;
        this.queue = queue;
    }

    @Override
    public void run() {
        runCustomers = true;
        for (int i = 0; runCustomers; ++i) {
            Order order = new Order(i);
            new Customer(this.queue).produce(order);
        }
    }

    public void stop() {
        runCustomers = false;
    }
}

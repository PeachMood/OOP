package ru.nsu.voronova.customer;

import ru.nsu.voronova.queue.MyBlockingDequeue;
import ru.nsu.voronova.order.Order;

/**
 * The Customers class simulates the behavior of the customers flow.
 * Customers create orders and place them in a shared order queue.
 */
public class Customers implements Runnable {
    private boolean runCustomers;
    private final MyBlockingDequeue<Order> queue;

    /**
     * Creates an instance of the class Customers.
     *
     * @param queue - shared order queue.
     */
    public Customers(MyBlockingDequeue<Order> queue) {
        this.runCustomers = false;
        this.queue = queue;
    }

    /**
     * Implementation of the Runnable interface. Starts the customers flow and stops only when stop method is called.
     */
    @Override
    public void run() {
        runCustomers = true;
        for (int i = 0; runCustomers; ++i) {
            Order order = new Order(i);
            new Customer(this.queue).produce(order);
        }
    }

    /**
     * Stops the flow of customers.
     */
    public void stop() {
        runCustomers = false;
    }
}

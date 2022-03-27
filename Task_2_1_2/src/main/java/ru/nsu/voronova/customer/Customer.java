package ru.nsu.voronova.customer;

import ru.nsu.voronova.order.Order;
import ru.nsu.voronova.producer.Producer;
import ru.nsu.voronova.queue.MyBlockingDequeue;
import ru.nsu.voronova.order.State;

import java.util.Random;

public class Customer implements Producer<Order> {
    private static final long MAX_ORDERING_TIME = 100;
    private final Random random;
    private final MyBlockingDequeue<Order> orders;

    public Customer(MyBlockingDequeue<Order> orders) {
        this.random = new Random();
        this.orders = orders;
    }

    @Override
    public void produce(Order order) {
        long orderingTime = random.nextLong(MAX_ORDERING_TIME);
        try {
            Thread.sleep(orderingTime);
            order.setState(State.IN_QUEUE);
            orders.put(order);
        } catch (NullPointerException exception) {
            System.err.println("A non-existent order was received from a customer.");
        } catch (InterruptedException exception) {
            System.err.println("The customer was unable to place an order №" + order.getId() + ".");
        }
    }
}

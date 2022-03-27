package ru.nsu.voronova.employee;

import ru.nsu.voronova.consumer.Consumer;
import ru.nsu.voronova.order.Order;
import ru.nsu.voronova.queue.MyBlockingDequeue;
import ru.nsu.voronova.order.State;

import java.util.List;
import java.util.Random;

import static ru.nsu.voronova.order.State.*;

public class Courier extends Employee implements Consumer<List<Order>> {
    private static final long MAX_DELIVERY_TIME = 100000;
    private int bagCapacity;
    private List<Order> orders;
    private final MyBlockingDequeue<Order> storage;
    private final Random random;

    public Courier(int id, int bagCapacity, MyBlockingDequeue<Order> storage) {
        super(id);
        this.bagCapacity = bagCapacity;
        this.storage = storage;
        this.random = new Random();
    }

    public void setBagCapacity(int bagCapacity) {
        this.bagCapacity = bagCapacity;
    }

    private void setOrdersState(State state) {
        for (Order order : orders) {
            order.setState(state);
        }
    }

    @Override
    public List<Order> consume() {
        long deliveryTime = random.nextLong(MAX_DELIVERY_TIME);
        try {
            orders = storage.get(bagCapacity);
            setOrdersState(DELIVERING);
            Thread.sleep(deliveryTime);
            setOrdersState(DELIVERED);
            return orders;
        } catch (InterruptedException exception) {
            System.err.println("The courier №" + getId() + " could not deliver the order.");
            return null;
        }
    }

    @Override
    void work() {
        List<Order> orders = consume();
        if (orders == null) {
            stop();
        }
    }
}

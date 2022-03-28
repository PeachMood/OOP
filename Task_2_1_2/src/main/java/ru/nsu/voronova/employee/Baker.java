package ru.nsu.voronova.employee;

import ru.nsu.voronova.consumer.Consumer;
import ru.nsu.voronova.order.Order;
import ru.nsu.voronova.producer.Producer;
import ru.nsu.voronova.queue.MyBlockingDequeue;

import java.util.Random;

import static ru.nsu.voronova.order.State.COOKING;
import static ru.nsu.voronova.order.State.IN_STOCK;

/**
 * The Baker class simulates the work of a baker.
 * The baker receives an order from the general queue of orders, within a certain time (depending on his work experience) makes an order and places it in the storage.
 */
public class Baker extends Employee implements Consumer<Order>, Producer<Order> {
    private static final long MAX_COOKING_TIME = 5000;
    private final int workingExperience;
    private final Random random;
    private final MyBlockingDequeue<Order> queue;
    private final MyBlockingDequeue<Order> storage;

    /**
     * Creates an instance of the class Baker.
     *
     * @param id                - baker's id.
     * @param workingExperience - baker's work experience.
     * @param queue             - shared order queue.
     * @param storage           - place of storage of finished orders.
     */
    public Baker(int id, int workingExperience, MyBlockingDequeue<Order> queue, MyBlockingDequeue<Order> storage) {
        super(id);
        this.workingExperience = workingExperience;
        this.random = new Random();
        this.queue = queue;
        this.storage = storage;
    }

    /**
     * Retrieves an order from the order queue.
     *
     * @return consumed order.
     */
    @Override
    public Order consume() {
        try {
            Order order = queue.get();
            order.setState(COOKING);
            return order;
        } catch (InterruptedException exception) {
            System.err.println("The baker №" + getId() + " could not take the order.");
            return null;
        }
    }

    /**
     * Places the finished order in the storage.
     *
     * @param order - object which should be produced.
     */
    @Override
    public void produce(Order order) {
        long leadTime = random.nextLong(MAX_COOKING_TIME) / workingExperience;
        try {
            Thread.sleep(leadTime);
            order.setState(IN_STOCK);
            storage.put(order);
        } catch (NullPointerException exception) {
            System.err.println("The baker №" + getId() + " tried to fulfill an order that does not exist.");
        } catch (InterruptedException exception) {
            System.err.println("The baker №" + getId() + "could not fulfill the order.");
        }
    }

    /**
     * Takes an order out of the order queue, executes the order within a certain time, and then sends it to the storage.
     * This method is used in the run method, which allows to simulate the constant work of a baker.
     * In case of failure, this method stops the run method.
     */
    @Override
    public void work() {
        Order order = consume();
        if (order == null) {
            stop();
        }
        produce(order);
    }
}

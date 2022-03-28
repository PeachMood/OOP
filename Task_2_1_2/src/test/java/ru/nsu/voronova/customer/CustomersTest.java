package ru.nsu.voronova.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.voronova.order.Order;
import ru.nsu.voronova.queue.MyBlockingDequeue;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.voronova.order.State.IN_QUEUE;

class CustomersTest {
    private final static int MAX_QUEUE_SIZE = 100;
    private MyBlockingDequeue<Order> queue;
    private int queueSize;
    private final Random random = new Random();

    @BeforeEach
    private void init() {
        queueSize = random.nextInt(MAX_QUEUE_SIZE);
        queue = new MyBlockingDequeue<>(queueSize);
    }

    @Test
    public void customers() throws InterruptedException {
        Customers customers = new Customers(queue);
        Thread customersThread = new Thread(new Customers(queue));
        customersThread.start();
        while (queue.getSize() != queueSize) {
        }
        Thread.sleep(100);
        customers.stop();
        List<Order> orders = queue.get(queueSize);
        orders.forEach(order -> assertEquals(IN_QUEUE, order.getState()));
    }
}
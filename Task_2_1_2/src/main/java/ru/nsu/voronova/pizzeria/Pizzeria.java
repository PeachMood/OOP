package ru.nsu.voronova.pizzeria;

import ru.nsu.voronova.customer.Customers;
import ru.nsu.voronova.employee.Baker;
import ru.nsu.voronova.employee.Courier;
import ru.nsu.voronova.json.BakerJSON;
import ru.nsu.voronova.json.CourierJSON;
import ru.nsu.voronova.json.PizzeriaJSON;
import ru.nsu.voronova.order.Order;
import ru.nsu.voronova.queue.MyBlockingDequeue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class simulates the operation of a pizzeria.
 */
public class Pizzeria implements Runnable {
    private boolean runPizzeria;
    private List<Baker> bakers;
    private List<Courier> couriers;
    private final Customers customers;
    private final MyBlockingDequeue<Order> queue;
    private final MyBlockingDequeue<Order> storage;

    private void setBakers(BakerJSON[] bakers) {
        Stream<BakerJSON> bakerJSONStream = Arrays.stream(bakers);
        this.bakers = bakerJSONStream
                .map(bakerJSON -> new Baker(bakerJSON.id(), bakerJSON.workingExperience(), this.queue, this.storage))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void setCouriers(CourierJSON[] couriers) {
        Stream<CourierJSON> courierJSONStream = Arrays.stream(couriers);
        this.couriers = courierJSONStream
                .map(courierJSON -> new Courier(courierJSON.id(), courierJSON.bagCapacity(), this.storage))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sets up a pizzeria based on the pizzeriaJson content.
     *
     * @param settings - pizzeria configuration.
     */
    public Pizzeria(PizzeriaJSON settings) {
        this.runPizzeria = false;
        this.queue = new MyBlockingDequeue<>(settings.queueSize());
        this.storage = new MyBlockingDequeue<>(settings.storageSize());
        this.customers = new Customers(this.queue);
        setBakers(settings.bakers());
        setCouriers(settings.couriers());
    }

    /**
     * Launches a pizzeria. The pizzeria stops working either when the stop method is called, or when an error occurs.
     */
    @Override
    public void run() {
        runPizzeria = true;
        ExecutorService bakersThreadPool = Executors.newFixedThreadPool(bakers.size());
        ExecutorService couriersThreadPool = Executors.newFixedThreadPool(couriers.size());
        bakers.forEach(bakersThreadPool::execute);
        couriers.forEach(couriersThreadPool::execute);
        Thread customersThread = new Thread(customers);
        customersThread.start();
        System.out.println("The pizzeria is up and running!");
        while (runPizzeria && !bakersThreadPool.isTerminated() && !couriersThreadPool.isTerminated()) {
        }
        if (bakersThreadPool.isTerminated() || couriersThreadPool.isTerminated()) {
            System.out.println("Oops, something went wrong. The pizzeria is closed for a technical break.");
        }
        runPizzeria = false;
        bakersThreadPool.shutdownNow();
        couriersThreadPool.shutdownNow();
        customers.stop();
    }

    /**
     * Stops the pizzeria from working.
     */
    public void stop() {
        System.out.println("The pizzeria is closed. Come visit us tomorrow!");
        runPizzeria = false;
    }
}

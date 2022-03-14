package ru.nsu.voronova;

public class Baker extends Producer {
    private int workingExperience;
    private final QueueBlocking<Order> orders;
    private final QueueBlocking<Order> storage;
    private final long MAX_COOKING_TIME = 100;

    public Baker(int workingExperience, QueueBlocking<Order> orders, QueueBlocking<Order> storage) {
        this.workingExperience = workingExperience;
        this.orders = orders;
        this.storage = storage;
    }

    public int getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(int workingExperience) {
        this.workingExperience = workingExperience;
    }

    @Override
    void produce() throws InterruptedException {
        Order order = orders.get();
        Thread.sleep(MAX_COOKING_TIME / workingExperience);
        storage.put(order);
    }
}

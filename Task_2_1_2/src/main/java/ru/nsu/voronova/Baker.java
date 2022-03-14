package ru.nsu.voronova;

public class Baker extends Consumer<Order> {
    private int workingExperience;
    private QueueBlocking<Order> storage;

    public Baker(int workingExperience, QueueBlocking<Order> orders, QueueBlocking<Order> storage) {
        super(orders);
        this.workingExperience = workingExperience;
        this.storage = storage;
    }

    public int getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(int workingExperience) {
        this.workingExperience = workingExperience;
    }

    @Override
    public void consume() {

    }
}

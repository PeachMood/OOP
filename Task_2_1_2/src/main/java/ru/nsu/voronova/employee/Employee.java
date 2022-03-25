package ru.nsu.voronova.employee;

public abstract class Employee implements Runnable {
    private final int id;
    private boolean isRunning;

    public Employee(int id) {
        this.id = id;
        this.isRunning = false;
    }

    public int getId() {
        return id;
    }

    abstract void work();

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            work();
        }
    }

    public void stop() {
        isRunning = false;
    }
}

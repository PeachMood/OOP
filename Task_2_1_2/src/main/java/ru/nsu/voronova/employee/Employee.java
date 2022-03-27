package ru.nsu.voronova.employee;

public abstract class Employee implements Runnable {
    private final int id;
    private boolean runEmployee;

    public Employee(int id) {
        this.id = id;
        this.runEmployee = false;
    }

    public int getId() {
        return id;
    }

    abstract void work();

    @Override
    public void run() {
        runEmployee = true;
        while (runEmployee) {
            work();
        }
    }

    public void stop() {
        runEmployee = false;
    }
}

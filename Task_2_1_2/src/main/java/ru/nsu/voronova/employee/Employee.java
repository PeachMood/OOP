package ru.nsu.voronova.employee;

/**
 * The implementation of this abstract method allows you to simulate the work of an employee.
 */
public abstract class Employee implements Runnable {
    private final int id;
    private boolean runEmployee;

    /**
     * Constructor of an abstract class. Allows to set the employee id.
     *
     * @param id - employee's id.
     */
    public Employee(int id) {
        this.id = id;
        this.runEmployee = false;
    }

    /**
     * Returns employee's id.
     *
     * @return - employee's id.
     */
    public int getId() {
        return id;
    }

    /**
     * The implementation of this abstract method should be the fulfillment of one task of the employee.
     */
    abstract void work();

    /**
     * Simulates the permanent work of an employee. Execution stops when the run method is called.
     */
    @Override
    public void run() {
        runEmployee = true;
        while (runEmployee) {
            work();
        }
    }

    /**
     * Stops the run method.
     */
    public void stop() {
        runEmployee = false;
    }
}

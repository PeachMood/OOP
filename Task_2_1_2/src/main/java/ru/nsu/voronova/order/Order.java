package ru.nsu.voronova.order;

/**
 * The Order class describes an order with a unique number and a ready state.
 */
public class Order {
    private final int id;
    private State state;

    /**
     * Creates an instance of the class Order.
     *
     * @param id - order's id.
     */
    public Order(int id) {
        this.id = id;
    }

    /**
     * Returns order's id.
     *
     * @return order's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns order's state.
     *
     * @return order's state.
     */
    public State getState() {
        return state;
    }

    /**
     * Changes the order status and prints a new state
     *
     * @param state - new order's state.
     */
    public void setState(State state) {
        this.state = state;
        System.out.println(this);
    }

    /**
     * Represents the order as a string.
     *
     * @return order.
     */
    @Override
    public String toString() {
        return "[Order №" + id + "], [" + state.getState() + "]";
    }
}

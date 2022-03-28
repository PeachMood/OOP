package ru.nsu.voronova.order;

/**
 * Describes the order status.
 */
public enum State {
    IN_QUEUE("in queue"), COOKING("cooking"), IN_STOCK("in stock"),
    DELIVERING("delivering"), DELIVERED("delivered");
    private final String state;

    State(String state) {
        this.state = state;
    }

    /**
     * Returns state as a string.
     *
     * @return state.
     */
    public String getState() {
        return state;
    }
}

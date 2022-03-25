package ru.nsu.voronova.order;

public enum State {
    IN_QUEUE("in queue"), COOKING("cooking"), IN_STOCK("in stock"),
    DELIVERING("delivering"), DELIVERED("delivered");
    private final String state;

    State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

package ru.nsu.voronova;

public class Order {
    private int number;

    public Order(int number) {
        this.number = number;
    }


    private enum State {
        IN_QUEUE("is in the queue"), COOKING("is being cooked"), IN_STOCK("is in the storage"),
        DELIVERING("is being delivered by courier"), DELIVERED("was delivered");
        private final String state;

        State(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }
}

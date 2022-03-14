package ru.nsu.voronova;

public abstract class Consumer<T> implements Runnable {
    private boolean isRunning;
    private QueueBlocking<T> queue;

    public Consumer(QueueBlocking<T> queue) {
        this.isRunning = false;
        this.queue = queue;
    }

    public abstract void consume();

    @Override
    public void run() {
        consume();
    }

    public void stop() {
        isRunning = false;
    }
}

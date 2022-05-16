package ru.nsu.voronova.snakegame.sprite;

public interface Sprite {
    Object getBoundary();
    void update(double x, double y);
    void render(Object object);
    boolean intersects(Sprite sprite);
}

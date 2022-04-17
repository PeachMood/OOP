package ru.nsu.voronova.snake.sprite;

import javafx.geometry.Rectangle2D;

public class Sprite {
    private final double width;
    private final double height;
    private double positionX;
    private double positionY;

    public Sprite(double width, double height) {
        this.width = width;
        this.height = height;
        this.positionX = 0;
        this.positionY = 0;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(getPositionX(), getPositionY(), width, height);
    }

    public boolean intersects(Sprite sprite) {
        return sprite.getBoundary().intersects(this.getBoundary());
    }
}


package ru.nsu.voronova.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.*;

public class Sprite {
    private ImageView imageView;
    private double width;
    private double height;
    private double positionX;
    private double positionY;

    public Sprite() {
        positionX = 0;
        positionY = 0;
    }

    public void setImage(ImageView imageView) {
        this.imageView = imageView;
        width = imageView.getFitWidth();
        height = imageView.getFitHeight();
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

    public ImageView getImageView() {
        imageView.setX(positionX);
        imageView.setY(positionY);
        return imageView;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(getPositionX(), getPositionY(), width, height);
    }

    public boolean intersects(Sprite sprite) {
        return sprite.getBoundary().intersects(this.getBoundary());
    }
}


package ru.nsu.voronova.skin;

import javafx.scene.image.*;

public class Skin {
    private double width;
    private double height;
    private final Image image;

    public Skin(double width, double height, Image image) {
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public ImageView getSkin() {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.preserveRatioProperty();
        return imageView;
    }

    public ImageView getRotatedSkin(double degrees) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setRotate(degrees);
        imageView.preserveRatioProperty();
        return imageView;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

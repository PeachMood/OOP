package ru.nsu.voronova.snakefx.skin;

import javafx.scene.image.*;

public class Skin {
    private final double width;
    private final double height;
    private final Image image;

    public Skin(double width, double height, Image image) {
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public ImageView getImage() {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.preserveRatioProperty();
        return imageView;
    }

    public ImageView getRotatedImage(double degrees) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setRotate(degrees);
        imageView.preserveRatioProperty();
        return imageView;
    }
}

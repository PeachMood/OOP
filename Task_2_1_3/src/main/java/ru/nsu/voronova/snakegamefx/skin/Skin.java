package ru.nsu.voronova.snakegamefx.skin;

import javafx.scene.image.*;

/**
 * Skin class is for working with images. Allows to set an image size and rotate it.
 */
public class Skin {
    private final double width;
    private final double height;
    private final Image image;

    /**
     * Class constructor. Defines image and its size.
     *
     * @param width  - image width.
     * @param height - image height.
     * @param image  - image of the skin.
     */
    public Skin(double width, double height, Image image) {
        this.width = width;
        this.height = height;
        this.image = image;
    }

    /**
     * Returns an image of set width and height.
     *
     * @return an image of set size.
     */
    public ImageView getImage() {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.preserveRatioProperty();
        return imageView;
    }

    /**
     * Returns an image of set width and height with specified rotation.
     *
     * @param degrees - image rotation angle clockwise.
     * @return an image of set size with specified rotation.
     */
    public ImageView getRotatedImage(double degrees) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setRotate(degrees);
        imageView.preserveRatioProperty();
        return imageView;
    }
}

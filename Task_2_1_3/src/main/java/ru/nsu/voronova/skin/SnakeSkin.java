package ru.nsu.voronova.skin;

import javafx.scene.image.Image;

public class SnakeSkin {
    private final double width;
    private final double height;
    private Skin headSkin;
    private Skin rotatedBodySkin;
    private Skin straightBodySkin;
    private Skin tailSkin;

    public SnakeSkin(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Skin getHeadSkin() {
        return headSkin;
    }

    public Skin getRotatedBodySkin() {
        return rotatedBodySkin;
    }

    public Skin getStraightBodySkin() {
        return straightBodySkin;
    }

    public Skin getTailSkin() {
        return tailSkin;
    }

    public void setHeadSkin(Image image) {
        headSkin = new Skin(width, height, image);
    }

    public void setRotatedBodySkin(Image image) {
        rotatedBodySkin = new Skin(width, height, image);
    }

    public void setStraightBodySkin(Image image) {
        straightBodySkin = new Skin(width, height, image);
    }

    public void setTailSkin(Image image) {
        tailSkin = new Skin(width, height, image);
    }
}

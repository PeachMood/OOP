package ru.nsu.voronova.snakegame.sprite;

/**
 * This class is a model of the game object.
 */
public interface Sprite {
    /**
     * Returns sprite's boundary.
     *
     * @return sprite's boundary.
     */
    Object getBoundary();

    /**
     * Changes sprite's coordinates.
     *
     * @param x -  new x coordinate.
     * @param y - new y coordinate.
     */
    void update(double x, double y);

    /**
     * Determine if the sprite intersects with another sprite.
     *
     * @param sprite - another sprite.
     * @return true if the sprite intersects with another sprite.
     */
    boolean intersects(Sprite sprite);

    /**
     * Renders the sprite.
     *
     * @param object - rendered sprite.
     */
    void render(Object object);
}

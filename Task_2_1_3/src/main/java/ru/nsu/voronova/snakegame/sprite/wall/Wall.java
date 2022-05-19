package ru.nsu.voronova.snakegame.sprite.wall;

import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.Sprite;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;

import java.util.List;
import java.util.Random;

/**
 * Wall model for the snake game.
 */
public abstract class Wall implements Sprite {
    private final Cell wall;
    private final Random random;

    /**
     * Class constructor.
     *
     * @param width  - wall's width.
     * @param height - wall's height.
     */
    public Wall(double width, double height) {
        this.wall = new Cell(width, height);
        this.random = new Random();
    }

    /**
     * Returns wall's boundary.
     *
     * @return wall's boundary.
     */
    @Override
    public Cell getBoundary() {
        return wall;
    }

    /**
     * Determine if the sprite intersects with another sprite.
     *
     * @param sprite - another sprite.
     * @return true if the sprite intersects with another sprite.
     */
    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite.getBoundary() instanceof Cell boundary) {
            return wall != boundary && wall.intersects(boundary);
        }
        if (sprite instanceof Snake snake) {
            List<Cell> boundary = snake.getBoundary();
            return boundary.stream().anyMatch(cell -> wall != cell && wall.intersects(cell));
        }
        return false;
    }

    /**
     * Changes wall's coordinates.
     *
     * @param rowsNumber    -  upper bound for x coordinate generation.
     * @param columnsNumber - upper bound for y coordinate generation.
     */
    @Override
    public void update(double rowsNumber, double columnsNumber) {
        double wallX = random.nextInt((int) rowsNumber - 1);
        double wallY = random.nextInt((int) columnsNumber - 1);
        wall.setPosition(wallX * wall.getWidth(), wallY * wall.getHeight());
    }

    /**
     * Renders the sprite.
     *
     * @param object - rendered sprite.
     */
    @Override
    public abstract void render(Object object);
}

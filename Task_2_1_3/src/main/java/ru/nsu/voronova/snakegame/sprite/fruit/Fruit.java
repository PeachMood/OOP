package ru.nsu.voronova.snakegame.sprite.fruit;

import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.Sprite;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Fruit that the snake eats in the snake game.
 */
public abstract class Fruit implements Sprite {
    private final Cell fruit;
    private final Random random;

    /**
     * Class constructor.
     * @param width - snake flake's width.
     * @param height - snake flake's height.
     */
    public Fruit(double width, double height) {
        this.fruit = new Cell(width, height);
        this.random = new Random();
    }

    /**
     * Returns fruit's boundary.
     *
     * @return fruit's boundary.
     */
    @Override
    public Cell getBoundary() {
        return fruit;
    }

    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite.getBoundary() instanceof Cell boundary) {
            return fruit != boundary && fruit.intersects(boundary);
        }
        if (sprite instanceof Snake snake) {
            List<Cell> boundary = snake.getBoundary();
            return boundary.stream().anyMatch(cell -> fruit != cell && fruit.intersects(cell));
        }
        return false;
    }


    /**
     * Changes fruit's coordinates.
     *
     * @param rowsNumber -  upper bound for x coordinate generation.
     * @param columnsNumber - upper bound for y coordinate generation.
     */
    @Override
    public void update(double rowsNumber, double columnsNumber) {
        double fruitX = random.nextInt((int) rowsNumber - 1);
        double fruitY = random.nextInt((int) columnsNumber - 1);
        fruit.setPosition(fruitX * fruit.getWidth(), fruitY * fruit.getHeight());
    }

    /**
     * Renders fruit.
     * @param object - rendered sprite.
     */
    @Override
    public abstract void render(Object object);
}

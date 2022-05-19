package ru.nsu.voronova.snakegame.sprite.board;

import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.Sprite;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;

import java.util.Arrays;
import java.util.List;

/**
 * A board for the snake game.
 */
public abstract class Board implements Sprite {
    private final Cell board;

    /**
     * Class constructor.
     *
     * @param width  - board's width.
     * @param height - board's height.
     */
    public Board(double width, double height) {
        board = new Cell(width, height);
    }

    /**
     * Returns sprite's boundary.
     *
     * @return sprite's boundary.
     */
    @Override
    public Cell getBoundary() {
        return board;
    }

    /**
     * Determine if the sprite intersects with another sprite.
     *
     * @param sprite - another sprite.
     * @return true if the sprite intersects with another sprite.
     */
    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite.getBoundary() instanceof Cell cell) {
            return board != cell && board.intersects(cell);
        }
        if (sprite instanceof Snake snake) {
            List<Cell> boundary = snake.getBoundary();
            return boundary.stream().anyMatch(cell -> board != cell && board.intersects(cell));
        }
        return false;
    }

    /**
     * Changes sprite's coordinates.
     *
     * @param x -  new x coordinate.
     * @param y - new y coordinate.
     */
    @Override
    public void update(double x, double y) {
        board.setPosition(x, y);
    }

    /**
     * Renders the sprite.
     *
     * @param object - rendered sprite.
     */
    @Override
    public abstract void render(Object object);
}

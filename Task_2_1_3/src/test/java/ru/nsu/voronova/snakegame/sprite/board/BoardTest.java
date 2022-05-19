package ru.nsu.voronova.snakegame.sprite.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;
import ru.nsu.voronova.snakegamefx.sprite.BoardFX;
import ru.nsu.voronova.snakegamefx.sprite.FruitFX;
import ru.nsu.voronova.snakegamefx.sprite.SnakeFX;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private final int ROWS_NUMBER = 10;
    private final int COLUMNS_NUMBER = ROWS_NUMBER;
    private final int SQUARE_SIZE = 20;
    private Board board;

    @BeforeEach
    private void initialize() {
        board = new BoardFX(ROWS_NUMBER * SQUARE_SIZE, COLUMNS_NUMBER * SQUARE_SIZE);
    }

    @Test
    void getBoundary() {
        Cell actual = board.getBoundary();
        Cell expected = new Cell(ROWS_NUMBER * SQUARE_SIZE, COLUMNS_NUMBER * SQUARE_SIZE);
        assertEquals(expected, actual);
    }

    @Test
    void intersects() {
        Fruit fruit = new FruitFX(SQUARE_SIZE, SQUARE_SIZE);
        fruit.update(ROWS_NUMBER, COLUMNS_NUMBER);
        board.intersects(fruit);
        Snake snake = new SnakeFX(SQUARE_SIZE, SQUARE_SIZE);
        snake.start(snake.getLength() * SQUARE_SIZE, (COLUMNS_NUMBER << 1) * SQUARE_SIZE);
        board.intersects(snake);
    }

    @Test
    void update() {
        board.update(SQUARE_SIZE, SQUARE_SIZE);
        Cell boundary = board.getBoundary();
        assertTrue(boundary.getX() == SQUARE_SIZE && boundary.getY() == SQUARE_SIZE);
    }
}
package ru.nsu.voronova.snakegame.sprite.wall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;
import ru.nsu.voronova.snakegamefx.sprite.FruitFX;
import ru.nsu.voronova.snakegamefx.sprite.SnakeFX;
import ru.nsu.voronova.snakegamefx.sprite.WallFX;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private final int SQUARE_SIZE = 20;
    private WallFX wall;

    @BeforeEach
    private void initialize() {
        wall = new WallFX(SQUARE_SIZE, SQUARE_SIZE);
    }

    @Test
    public void getBoundary() {
        Cell actual = wall.getBoundary();
        Cell expected = new Cell(SQUARE_SIZE, SQUARE_SIZE);
        expected.setPosition(0, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void intersects() {
        Wall wall = new WallFX(SQUARE_SIZE, SQUARE_SIZE);
        assertTrue(this.wall.intersects(wall));
        Snake snake = new SnakeFX(SQUARE_SIZE, SQUARE_SIZE);
        snake.start(0, 0);
        assertTrue(this.wall.intersects(snake));
    }

    @Test
    public void update() {
        int maxX = 10 * SQUARE_SIZE;
        int maxY = 10 * SQUARE_SIZE;
        wall.update(10, 10);
        Cell boundary = wall.getBoundary();
        assertTrue(boundary.getX() <= maxX && boundary.getY() <= maxY);
    }
}
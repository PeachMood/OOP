package ru.nsu.voronova.snakegame.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    private final double WIDTH = 10;
    private final double HEIGHT = 20;
    private final double X = -5;
    private final double Y = 3;
    private Cell cell;

    @BeforeEach
    private void initialize() {
        cell = new Cell(WIDTH, HEIGHT);
    }

    @Test
    public void getWidth() {
        cell = new Cell(WIDTH, HEIGHT);
        assertEquals(WIDTH, cell.getWidth());
    }

    @Test
    public void getHeight() {
        cell = new Cell(WIDTH, HEIGHT);
        assertEquals(HEIGHT, cell.getHeight());
    }

    @Test
    public void getX() {
        cell.setPosition(X, Y);
        assertEquals(X, cell.getX());
    }

    @Test
    public void getY() {
        cell.setPosition(X, Y);
        assertEquals(Y, cell.getY());
    }

    @Test
    public void intersects() {
        Cell cell = new Cell(1000, 1000);
        assertTrue(this.cell.intersects(cell));
    }

    @Test
    public void testEquals() {
        Cell cell = new Cell(WIDTH, HEIGHT);
        assertEquals(this.cell, this.cell);
        assertEquals(cell, this.cell);
        cell.setPosition(X, Y);
        assertNotEquals(cell, this.cell);
    }
}
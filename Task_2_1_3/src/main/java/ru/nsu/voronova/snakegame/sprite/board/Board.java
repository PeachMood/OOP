package ru.nsu.voronova.snakegame.sprite.board;

import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.Sprite;

import java.util.Arrays;

public abstract class Board implements Sprite {
    private final Cell board;

    public Board(double width, double height) {
        board = new Cell(width, height);
    }

    @Override
    public Cell getBoundary() {
        return board.clone();
    }

    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite.getBoundary() instanceof Cell cell) {
            return board != cell && board.intersects(cell);
        }
        if (sprite.getBoundary() instanceof Cell[] boundary) {
            return Arrays.stream(boundary).anyMatch(cell -> board != cell && board.intersects(cell));
        }
        return false;
    }

    @Override
    public void update(double x, double y) {
        board.setPosition(x, y);
    }

    @Override
    public abstract void render(Object object);
}

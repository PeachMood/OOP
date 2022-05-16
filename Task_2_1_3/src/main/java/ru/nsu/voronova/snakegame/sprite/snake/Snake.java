package ru.nsu.voronova.snakegame.sprite.snake;

import ru.nsu.voronova.snakegame.sprite.Sprite;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.board.Board;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.nsu.voronova.snakegame.sprite.snake.Direction.*;

public abstract class Snake implements Sprite {
    private final int INITIAL_LENGTH = 3;
    private Direction direction;
    private final Cell head;
    private final List<Cell> body;

    public Snake(double width, double height) {
        this.body = Stream.generate(() -> new Cell(width, height)).limit(INITIAL_LENGTH).collect(Collectors.toCollection(ArrayList::new));
        this.head = this.body.get(0);
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return body.size();
    }

    public void setDirection(Direction direction) {
        if ((this.direction == UP && direction == DOWN) || (this.direction == DOWN && direction == UP)) {
            return;
        }
        if ((this.direction == LEFT && direction == RIGHT) || (this.direction == RIGHT && direction == LEFT)) {
            return;
        }
        this.direction = direction;
    }

    public void start(double headPositionX, double headPositionY) {
        setDirection(RIGHT);
        head.setPosition(headPositionX, headPositionY);
        for (int i = 1; i < INITIAL_LENGTH; ++i) {
            body.get(i).setPosition(body.get(i - 1).getPositionX() - head.getWidth(), headPositionY);
        }
    }

    public void grow() {
        Cell flake = new Cell(head.getWidth(), head.getHeight());
        flake.setPosition(-1, -1);
        body.add(flake);
    }

    @Override
    public Cell[] getBoundary() {
        List<Cell> clone = new ArrayList<>();
        body.forEach(cell -> clone.add(cell.clone()));
        return clone.toArray(new Cell[0]);
    }

    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite == this) {
            return body.stream().anyMatch((cell -> !cell.equals(head) && cell.intersects(head)));
        }
        if (sprite instanceof Board) {
            return head.intersects((Cell) sprite.getBoundary());
        }
        if (sprite instanceof Fruit) {
            return body.stream().anyMatch((cell -> cell.intersects((Cell) sprite.getBoundary())));
        }
        return false;
    }

    @Override
    public void update(double x, double y) {
        int length = getLength();
        for (int i = length - 1; i >= 1; --i) {
            body.get(i).setPosition(body.get(i - 1).getPositionX(), body.get(i - 1).getPositionY());
        }
        switch (direction) {
            case RIGHT -> head.setPosition(head.getPositionX() + x, head.getPositionY());
            case LEFT -> head.setPosition(head.getPositionX() - x, head.getPositionY());
            case UP -> head.setPosition(head.getPositionX(), head.getPositionY() - y);
            case DOWN -> head.setPosition(head.getPositionX(), head.getPositionY() + y);
        }
    }

    @Override
    public abstract void render(Object object);
}

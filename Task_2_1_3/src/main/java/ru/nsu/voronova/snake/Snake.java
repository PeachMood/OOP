package ru.nsu.voronova.snake;

import ru.nsu.voronova.direction.Direction;
import ru.nsu.voronova.snake.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.nsu.voronova.direction.Direction.RIGHT;

public abstract class Snake {
    private Direction direction;
    private int length;
    private final double width;
    private final double height;
    private final Sprite head;
    private final List<Sprite> body;

    public Snake(int length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.body = Stream.generate(() -> new Sprite(width, height)).limit(length).collect(Collectors.toCollection(ArrayList::new));
        this.head = this.body.get(0);
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    public List<Sprite> getBody() {
        return new ArrayList<>(body);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void start(double headPositionX, double headPositionY) {
        setDirection(RIGHT);
        head.setPosition(headPositionX, headPositionY);
        for (int i = 1; i < length; ++i) {
            body.get(i).setPosition(body.get(i - 1).getPositionX() - width, headPositionY);
        }
    }

    public void grow() {
        Sprite flake = new Sprite(width, height);
        flake.setPosition(-1, -1);
        body.add(flake);
        length++;
    }

    public boolean intersects(Object object) {
        if (object == this) {
            return body.stream().anyMatch(flake -> flake != head && flake.intersects(head));
        }
        if (object instanceof Sprite) {
            return head.intersects((Sprite) object);
        }
        return false;
    }

    public void move() {
        double stepX = width;
        double stepY = height;
        for (int i = length - 1; i >= 1; --i) {
            body.get(i).setPosition(body.get(i - 1).getPositionX(), body.get(i - 1).getPositionY());
        }
        switch (direction) {
            case RIGHT -> head.setPosition(head.getPositionX() + stepX, head.getPositionY());
            case LEFT -> head.setPosition(head.getPositionX() - stepX, head.getPositionY());
            case UP -> head.setPosition(head.getPositionX(), head.getPositionY() - stepY);
            case DOWN -> head.setPosition(head.getPositionX(), head.getPositionY() + stepY);
        }
    }

    public abstract Object render();
}

package ru.nsu.voronova.snakegame.sprite.snake;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public boolean opposite(Direction direction) {
        return ((this == UP && direction == DOWN) ||
                (this == DOWN && direction == UP) ||
                (this == LEFT && direction == RIGHT) ||
                (this == RIGHT && direction == LEFT));
    }
}

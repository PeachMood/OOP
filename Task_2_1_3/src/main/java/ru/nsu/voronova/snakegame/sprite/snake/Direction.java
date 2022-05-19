package ru.nsu.voronova.snakegame.sprite.snake;

/**
 * This class denotes all possible snake directions in the game.
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * Checks if the specified direction is the opposite of the current one.
     *
     * @param direction - the specified direction.
     * @return true if the specified direction is the opposite of the current one.
     */
    public boolean opposite(Direction direction) {
        return ((this == UP && direction == DOWN) ||
                (this == DOWN && direction == UP) ||
                (this == LEFT && direction == RIGHT) ||
                (this == RIGHT && direction == LEFT));
    }
}

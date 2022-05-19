package ru.nsu.voronova.application.configuration;

/**
 * This record is a snake game configuration. The configuration is set by the user.
 *
 * @param squareSize    - the size of squares on the playing field.
 * @param rowsNumber    - the number of rows on the playing field.
 * @param columnsNumber - the number of columns on the playing field.
 * @param maximumScore  - the number of apples it is needed to eat to win.
 * @param wallsNumber   - the number of walls that will be on the field during the game
 * @param fruitsNumber  - the number of fruits on the field at each moment of the game
 * @param snakeSpeed    - the speed of the snake across the field (frame refresh rate).
 */
public record Configuration(double squareSize, int rowsNumber, int columnsNumber, int maximumScore, int wallsNumber,
                            int fruitsNumber, int snakeSpeed) {
}

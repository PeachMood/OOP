package ru.nsu.voronova.snakegame.game;

import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.snakegame.sprite.Sprite;
import ru.nsu.voronova.snakegame.sprite.board.Board;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegame.sprite.snake.Direction;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;
import ru.nsu.voronova.snakegame.sprite.wall.Wall;

import java.util.List;

import static ru.nsu.voronova.snakegame.game.GameState.*;

/**
 * The model for the snake game.
 */
public class Game {
    private final Configuration configuration;
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;
    private final List<Wall> walls;
    private GameState gameState;

    /**
     * Class constructor.
     * @param configuration - the current configuration of the game.
     * @param board - the model of the board for the game.
     * @param snake - the model of the snake for the game.
     * @param food - the model of the fruits for the game.
     * @param walls - the model of the walls for the game.
     */
    public Game(Configuration configuration, Board board, Snake snake, List<Fruit> food, List<Wall> walls) {
        this.configuration = configuration;
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.walls = walls;
        this.gameState = PLAY;
    }

    /**
     * Returns current score.
     * @return current score.
     */
    public int getScore() {
        return snake.getLength() - 3;
    }

    /**
     * Returns current snake game state.
     * @return current snake game state.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Changes snake direction.
     * @param direction - new snake direction.
     */
    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    private void updateGameState() {
        if (!snake.intersects(board) || snake.intersects(snake) || walls.stream().anyMatch(snake::intersects)) {
            gameState = DEFEAT;
        } else if (getScore() == configuration.maximumScore()) {
            gameState = VICTORY;
        } else {
            gameState = PLAY;
        }
    }

    private void updateSprite(Sprite sprite) {
        do {
            sprite.update(configuration.rowsNumber(), configuration.columnsNumber());
        } while (snake.intersects(sprite) ||
                food.stream().anyMatch(other -> other != sprite && other.intersects(sprite)) ||
                walls.stream().anyMatch(other -> other != sprite && other.intersects(sprite)));
    }

    private void eatFood() {
        for (Fruit fruit : food) {
            if (snake.intersects(fruit)) {
                snake.grow();
                updateSprite(fruit);
            }
        }
    }

    /**
     * Initialize new snake game.
     */
    public void start() {
        snake.start(snake.getLength() * configuration.squareSize(), (configuration.columnsNumber() >> 1) * configuration.squareSize());
        walls.forEach(this::updateSprite);
        food.forEach(this::updateSprite);
    }

    /**
     * Update all components of the game.
     */
    public void update() {
        if (gameState == PLAY) {
            eatFood();
            snake.update(configuration.squareSize(), configuration.squareSize());
            updateGameState();
        }
    }
}

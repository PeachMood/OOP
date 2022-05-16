package ru.nsu.voronova.snakegame.game;

import ru.nsu.voronova.snakegame.game.state.GameState;
import ru.nsu.voronova.snakegame.Configuration;
import ru.nsu.voronova.snakegame.sprite.snake.Direction;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;
import ru.nsu.voronova.snakegame.sprite.board.Board;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;

import java.util.List;

import static ru.nsu.voronova.snakegame.game.state.GameState.*;

public class SnakeGame implements Runnable {
    private final Configuration configuration;
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;
    private GameState gameState;

    public SnakeGame(Configuration configuration, Board board, Snake snake, List<Fruit> food) {
        this.configuration = configuration;
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.gameState = PAUSE;
    }

    public int getScore() {
        return snake.getLength() - 3;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    private void updateGameState() {
        if (!snake.intersects(board) || snake.intersects(snake)) {
            gameState = DEFEAT;
        } else if (getScore() == configuration.maximumScore()) {
            gameState = VICTORY;
        } else {
            gameState = PLAY;
        }
    }

    private void updateFruit(Fruit fruit) {
        do {
            fruit.update(configuration.rowsNumber(), configuration.columnsNumber());
        } while (snake.intersects(fruit) || food.stream().anyMatch(other -> other != fruit && other.intersects(fruit)));
    }

    private void eatFood() {
        for (Fruit fruit : food) {
            if (snake.intersects(fruit)) {
                snake.grow();
                updateFruit(fruit);
            }
        }
    }

    public void start() {
        snake.start(snake.getLength() * configuration.squareSize(), (configuration.columnsNumber() >> 1) * configuration.squareSize());
        food.forEach(this::updateFruit);
    }

    @Override
    public void run() {
        if (gameState == PLAY) {
            eatFood();
            snake.update(configuration.squareSize(), configuration.squareSize());
            updateGameState();
        }
    }
}

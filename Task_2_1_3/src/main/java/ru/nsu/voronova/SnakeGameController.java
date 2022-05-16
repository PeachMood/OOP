package ru.nsu.voronova;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.voronova.snakegame.game.SnakeGame;

import static ru.nsu.voronova.snakegame.game.state.GameState.PAUSE;
import static ru.nsu.voronova.snakegame.game.state.GameState.PLAY;
import static ru.nsu.voronova.snakegame.sprite.snake.Direction.*;
import static ru.nsu.voronova.snakegame.sprite.snake.Direction.DOWN;

public class SnakeGameController {
    private final SnakeGame snakeGame;
    @FXML
    private Label score;

    public SnakeGameController(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    public void onKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();
        if (snakeGame.getGameState() == PAUSE) {
            snakeGame.setGameState(PLAY);
            return;
        }
        switch (code) {
            case RIGHT -> snakeGame.setSnakeDirection(RIGHT);
            case LEFT -> snakeGame.setSnakeDirection(LEFT);
            case UP -> snakeGame.setSnakeDirection(UP);
            case DOWN -> snakeGame.setSnakeDirection(DOWN);
            case ESCAPE -> snakeGame.setGameState(PAUSE);
        }
    }

    public void setScore(int number) {
        score.setText("Score: " + number);
    }
}

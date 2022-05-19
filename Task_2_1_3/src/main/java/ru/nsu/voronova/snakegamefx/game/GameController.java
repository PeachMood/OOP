package ru.nsu.voronova.snakegamefx.game;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.voronova.application.snakegame.SnakeGameController;

import static javafx.animation.Animation.Status.PAUSED;
import static javafx.animation.Animation.Status.RUNNING;
import static ru.nsu.voronova.snakegame.game.GameState.*;
import static ru.nsu.voronova.snakegame.sprite.snake.Direction.*;
import static ru.nsu.voronova.snakegame.sprite.snake.Direction.DOWN;

/**
 * This class interacts with the components of the MVC model for the snake game
 */
public class GameController {
    private final SnakeGameController controller;
    private final GameFX snakeGame;
    private final Timeline timeline;

    /**
     * Class constructor.
     *
     * @param controller - controller for the snake game application.
     * @param snakeGame  - the model of the snake game.
     * @param timeline   - the instance of the class responsible for changing the frames of the snake game.
     */
    public GameController(SnakeGameController controller, GameFX snakeGame, Timeline timeline) {
        this.controller = controller;
        this.snakeGame = snakeGame;
        this.timeline = timeline;
    }

    /**
     * Handles key event.
     *
     * @param event - user pressing one of the keyboard buttons.
     */
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if (timeline.getStatus() == PAUSED) {
            timeline.play();
            return;
        }
        switch (code) {
            case RIGHT -> snakeGame.setSnakeDirection(RIGHT);
            case LEFT -> snakeGame.setSnakeDirection(LEFT);
            case UP -> snakeGame.setSnakeDirection(UP);
            case DOWN -> snakeGame.setSnakeDirection(DOWN);
            case ESCAPE -> controller.openModalWindow();
        }
    }

    /**
     * Runs snake game.
     *
     * @param frame - frame on which snake game should be rendered.
     */
    public void run(Group frame) {
        if (timeline.getStatus() == RUNNING && (snakeGame.getGameState() == DEFEAT || snakeGame.getGameState() == VICTORY)) {
            timeline.stop();
            controller.openModalWindow();
        }
        snakeGame.update();
        controller.updateScore();
        snakeGame.render(frame);
    }
}

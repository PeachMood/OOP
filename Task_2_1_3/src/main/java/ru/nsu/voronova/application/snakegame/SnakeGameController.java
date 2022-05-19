package ru.nsu.voronova.application.snakegame;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.application.modalwindow.ModalWindow;
import ru.nsu.voronova.snakegame.game.Game;

import static ru.nsu.voronova.snakegame.game.GameState.*;

/**
 * Snake game controller. Provides user interaction with the interface of the snake game.
 */
public class SnakeGameController {
    private ModalWindow modalWindow;
    private Timeline timeline;
    private Game game;

    @FXML
    private Label score;

    /**
     * Initializes the data necessary for the controller to work.
     * @param mainStage - the main window with the snake game.
     * @param configuration - the current configuration of the game.
     * @param timeline - the instance of the class responsible for changing the frames of the snake game.
     * @param game - the model of the snake game.
     */
    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline, Game game) {
        this.modalWindow = new ModalWindow(mainStage, configuration, timeline);
        this.game = game;
        this.timeline = timeline;
    }

    /**
     * Updates score number on the screen.
     */
    public void updateScore() {
        score.setText("Score: " + game.getScore());
    }

    /**
     * Opens modal window.
     */
    @FXML
    public void openModalWindow() {
        timeline.pause();
        if (game.getGameState() != DEFEAT && game.getGameState() != VICTORY) {
            modalWindow.open("PAUSE");
            return;
        }
        modalWindow.open(game.getGameState().toString());
    }
}

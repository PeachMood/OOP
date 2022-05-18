package ru.nsu.voronova.application.snakegame;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.application.modalwindow.ModalWindow;
import ru.nsu.voronova.snakegame.game.Game;

import static ru.nsu.voronova.snakegame.game.GameState.*;

public class SnakeGameController {
    private ModalWindow modalWindow;
    private Timeline timeline;
    private Game game;

    @FXML
    private Label score;

    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline, Game game) {
        this.modalWindow = new ModalWindow(mainStage, configuration, timeline);
        this.game = game;
        this.timeline = timeline;
    }

    public void updateScore() {
        score.setText("Score: " + game.getScore());
    }

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

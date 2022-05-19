package ru.nsu.voronova.application.modalwindow;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.voronova.application.snakegame.SnakeGame;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.application.menu.Menu;

/**
 * Modal Window controller. Helps the user and other classes interact with modal window elements.
 */
public class ModalWindowController {
    private Stage stage;
    private Configuration configuration;
    private Timeline timeline;

    @FXML
    StackPane modalWindow;
    @FXML
    private Label header;

    /**
     * Initializes the data necessary for the controller to work.
     *
     * @param mainStage     - the main window with the snake game.
     * @param configuration - the current configuration of the game.
     * @param timeline      - the instance of the class responsible for changing the frames of the snake game.
     */
    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline) {
        this.stage = mainStage;
        this.configuration = configuration;
        this.timeline = timeline;
    }

    /**
     * Changes the header of the modal window.
     *
     * @param text - the text, with which modal window should be opened.
     */
    public void setHeader(String text) {
        header.setText(text);
    }

    /**
     * Closes modal window.
     */
    private void closeModalWindow() {
        Stage stage = (Stage) modalWindow.getScene().getWindow();
        stage.close();
    }

    /**
     * When click a button with this handler, a new game starts.
     */
    @FXML
    private void restartGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
        closeModalWindow();
    }

    /**
     * When click a button with this handler, the current game continues.
     */
    @FXML
    private void continueGame() {
        timeline.play();
        closeModalWindow();
    }

    /**
     * Clicking on the button with the specified event handler opens a window with a menu.
     */
    @FXML
    private void openMenu() {
        Menu menu = new Menu(configuration);
        menu.setStage(stage);
        timeline.stop();
        closeModalWindow();
    }
}

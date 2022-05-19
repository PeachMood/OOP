package ru.nsu.voronova.application.menu;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import ru.nsu.voronova.application.snakegame.SnakeGame;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.application.settings.Settings;

/**
 * Menu controller. Helps the user and other classes interact with menu elements.
 */
public class MenuController {
    private Stage stage;
    private Configuration configuration;

    /**
     * Initializes the data necessary for the controller to work.
     *
     * @param stage         - the window in which the menu is open.
     * @param configuration - current snake game configuration.
     */
    public void initialize(Stage stage, Configuration configuration) {
        this.stage = stage;
        this.configuration = configuration;
    }

    /**
     * When click on the button with the specified handler,
     * a window opens with the snake game and the current configuration.
     */
    @FXML
    private void startGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
    }

    /**
     * When click on the button with the specified handler, the settings window opens.
     */
    @FXML
    private void openSettings() {
        Settings settings = new Settings(configuration);
        settings.setStage(stage);
    }

    /**
     * When click on the button with the specified handler, the application is shutting down.
     */
    @FXML
    private void exit() {
        stage.close();
    }
}

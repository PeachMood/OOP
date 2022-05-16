package ru.nsu.voronova;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import ru.nsu.voronova.snakegame.Configuration;

import java.io.IOException;

public class MenuController {
    private Stage stage;
    private Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void start() {
        SnakeGameFX snakeGame = new SnakeGameFX(configuration);
        snakeGame.setStage(stage);
    }

    @FXML
    private void settings() throws IOException {
        Settings settings = new Settings(configuration);
        settings.setStage(stage);
    }

    @FXML
    private void exit() throws IOException {
        stage.close();
    }
}

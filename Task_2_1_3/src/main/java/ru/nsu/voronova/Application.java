package ru.nsu.voronova;

import javafx.stage.Stage;
import ru.nsu.voronova.snakegame.Configuration;

public class Application extends javafx.application.Application {
    private SnakeGameFX snakeGame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake");
        Configuration configuration = new Configuration(40, 20, 20, 13, 10, 140);
        Menu menu = new Menu(configuration);
        menu.setStage(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

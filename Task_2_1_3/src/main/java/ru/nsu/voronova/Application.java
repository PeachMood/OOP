package ru.nsu.voronova;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.application.menu.Menu;

public class Application extends javafx.application.Application {
    private final Configuration DEFAULT_CONFIGURATION = new Configuration(40, 20, 20, 13, 0, 10, 100);
    private final Image ICON = new Image(String.valueOf(getClass().getResource("/ru/nsu/voronova/images/fruit/apple.png")));

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(ICON);
        Menu menu = new Menu(DEFAULT_CONFIGURATION);
        menu.setStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

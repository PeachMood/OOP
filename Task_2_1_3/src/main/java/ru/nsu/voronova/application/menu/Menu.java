package ru.nsu.voronova.application.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.voronova.application.configuration.Configuration;

import java.io.IOException;

/**
 * This class is designed to open the snake game menu in the specified window.
 */
public class Menu {
    private final Configuration configuration;
    private Scene scene;
    private MenuController controller;

    /**
     * Class constructor. Initializes a scene with a menu and a controller.
     *
     * @param configuration - the current configuration of the snake game.
     */
    public Menu(Configuration configuration) {
        this.configuration = configuration;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/voronova/fxml/menu.fxml"));
        try {
            Parent root = loader.load();
            scene = new Scene(root);
            controller = loader.getController();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Allows to open a menu in the specified window.
     *
     * @param stage - the window in which it is necessary to open the menu.
     */
    public void setStage(Stage stage) {
        if (scene != null && controller != null) {
            controller.initialize(stage, configuration);
            stage.setScene(scene);
        }
    }
}

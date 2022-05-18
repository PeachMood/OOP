package ru.nsu.voronova.application.settings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.voronova.application.configuration.Configuration;

import java.io.IOException;

public class Settings {
    private final Configuration configuration;
    private SettingsController controller;
    private Scene scene;

    public Settings(Configuration configuration) {
        this.configuration = configuration;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/voronova/fxml/settings.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            scene = new Scene(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        if (scene != null && controller != null) {
            controller.initialize(stage, configuration);
            stage.setScene(scene);
        }
    }
}

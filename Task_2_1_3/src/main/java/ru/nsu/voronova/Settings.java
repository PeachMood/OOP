package ru.nsu.voronova;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.voronova.snakegame.Configuration;

import java.io.IOException;

public class Settings {
    private final Configuration configuration;

    public Settings(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setStage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/voronova/fxml/settings.fxml"));
        Parent root = loader.load();
        SettingsController controller = loader.getController();
        Scene scene = new Scene(root);
        controller.setStage(stage);
        controller.setConfiguration(configuration);
        stage.setScene(scene);
    }
}

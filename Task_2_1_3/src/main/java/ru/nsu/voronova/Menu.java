package ru.nsu.voronova;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.voronova.snakegame.Configuration;

import java.io.IOException;

public class Menu {
    private final Configuration configuration;

    public Menu(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setStage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/voronova/fxml/menu.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        Scene scene = new Scene(root);
        controller.setStage(stage);
        controller.setConfiguration(configuration);
        stage.setScene(scene);
    }
}

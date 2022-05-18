package ru.nsu.voronova.application.modalwindow;

import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.voronova.application.configuration.Configuration;

import java.io.IOException;

public class ModalWindow {
    private Stage modalWindowStage;
    private ModalWindowController controller;

    public ModalWindow(Stage stage, Configuration configuration, Timeline timeline) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/voronova/fxml/modalWindow.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            controller.initialize(stage, configuration, timeline);
            modalWindowStage = new Stage();
            modalWindowStage.setScene(new Scene(root));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void open(String header) {
        if (controller != null && modalWindowStage != null && !modalWindowStage.isShowing()) {
            controller.setHeader(header);
            modalWindowStage.show();
        }
    }
}


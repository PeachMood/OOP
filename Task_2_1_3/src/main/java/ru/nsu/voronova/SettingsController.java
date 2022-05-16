package ru.nsu.voronova;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.nsu.voronova.snakegame.Configuration;

import java.io.IOException;
import java.util.Objects;

public class SettingsController {
    private Stage stage;
    private Configuration configuration;

    @FXML
    private Slider squareSize;
    @FXML
    private TextField rowsNumber;
    @FXML
    private TextField columnsNumber;
    @FXML
    private TextField maximumScore;
    @FXML
    private Slider fruitsNumber;
    @FXML
    private MenuButton snakeSpeed;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void chooseSlowly() {
        snakeSpeed.setText("Slowly");
    }

    @FXML
    private void chooseNormal() {
        snakeSpeed.setText("Normal");
    }

    @FXML
    private void chooseFast() {
        snakeSpeed.setText("Fast");
    }

    @FXML
    private void saveConfiguration() {
        double squareSize = this.squareSize.getValue();
        int rowsNumber = Objects.equals(this.rowsNumber.getText(), "") ? 0 : Integer.parseInt(this.rowsNumber.getText());
        int columnsNumber = Objects.equals(this.rowsNumber.getText(), "") ? 0 : Integer.parseInt(this.columnsNumber.getText());
        int maximumScore = Objects.equals(this.rowsNumber.getText(), "") ? 0 : Integer.parseInt(this.maximumScore.getText());
        int fruitsNumber = (int) this.fruitsNumber.getValue();
        int snakeSpeed = switch (this.snakeSpeed.getText()) {
            case "Slow" -> 300;
            case "Fast" -> 120;
            case "Very fast" -> 100;
            default -> 150;
        };
        this.configuration = new Configuration(squareSize, rowsNumber, columnsNumber, maximumScore, fruitsNumber, snakeSpeed);
    }

    @FXML
    private void openMenu() throws IOException {
        Menu menu = new Menu(configuration);
        menu.setStage(stage);
    }
}

package ru.nsu.voronova.application.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.application.menu.Menu;

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

    public void initialize(Stage mainStage, Configuration configuration) {
        this.stage = mainStage;
        this.configuration = configuration;
        squareSize.setValue(configuration.squareSize());
        rowsNumber.setText(String.valueOf(configuration.rowsNumber()));
        columnsNumber.setText(String.valueOf(configuration.columnsNumber()));
        maximumScore.setText(String.valueOf(configuration.maximumScore()));
        fruitsNumber.setValue(configuration.fruitsNumber());
    }

    @FXML
    private void changeSpeed(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        snakeSpeed.setText(menuItem.getText());
    }

    @FXML
    private void saveConfiguration() {
        double squareSize = this.squareSize.getValue();
        int rowsNumber = Objects.equals(this.rowsNumber.getText(), "") ? configuration.rowsNumber() : Integer.parseInt(this.rowsNumber.getText());
        int columnsNumber = Objects.equals(this.rowsNumber.getText(), "") ? configuration.columnsNumber() : Integer.parseInt(this.columnsNumber.getText());
        int maximumScore = Objects.equals(this.rowsNumber.getText(), "") ? configuration.maximumScore() : Integer.parseInt(this.maximumScore.getText());
        int fruitsNumber = (int) this.fruitsNumber.getValue();
        int snakeSpeed = switch (this.snakeSpeed.getText()) {
            case "Slowly" -> 300;
            case "Normal" -> 120;
            case "Fast" -> 100;
            default -> 150;
        };
        this.configuration = new Configuration(squareSize, rowsNumber, columnsNumber, maximumScore, fruitsNumber, snakeSpeed);
        openMenu();
    }

    @FXML
    private void openMenu() {
        ru.nsu.voronova.application.menu.Menu menu = new Menu(configuration);
        menu.setStage(stage);
    }
}

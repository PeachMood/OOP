module ru.nsu.voronova {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.voronova to javafx.fxml;
    exports ru.nsu.voronova;
    exports ru.nsu.voronova.skin;
    opens ru.nsu.voronova.skin to javafx.fxml;
    exports ru.nsu.voronova.sprite;
    opens ru.nsu.voronova.sprite to javafx.fxml;
    exports ru.nsu.voronova.snake;
    opens ru.nsu.voronova.snake to javafx.fxml;
    exports ru.nsu.voronova.direction;
    opens ru.nsu.voronova.direction to javafx.fxml;
}
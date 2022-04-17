module ru.nsu.voronova {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.voronova to javafx.fxml;
    exports ru.nsu.voronova;
    exports ru.nsu.voronova.snakefx.skin;
    opens ru.nsu.voronova.snakefx.skin to javafx.fxml;
    exports ru.nsu.voronova.snake.sprite;
    opens ru.nsu.voronova.snake.sprite to javafx.fxml;
    exports ru.nsu.voronova.direction;
    opens ru.nsu.voronova.direction to javafx.fxml;
    exports ru.nsu.voronova.snake;
    opens ru.nsu.voronova.snake to javafx.fxml;
    exports ru.nsu.voronova.snakefx;
    opens ru.nsu.voronova.snakefx to javafx.fxml;
}
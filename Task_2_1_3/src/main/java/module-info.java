module ru.nsu.voronova {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.voronova to javafx.fxml;
    exports ru.nsu.voronova;
    exports ru.nsu.voronova.snakegame.sprite;
    opens ru.nsu.voronova.snakegame.sprite to javafx.fxml;
    exports ru.nsu.voronova.snakegame.cell;
    opens ru.nsu.voronova.snakegame.cell to javafx.fxml;
    exports ru.nsu.voronova.snakegamefx.skin;
    opens ru.nsu.voronova.snakegamefx.skin to javafx.fxml;
    exports ru.nsu.voronova.snakegame.game;
    opens ru.nsu.voronova.snakegame.game to javafx.fxml;
    exports ru.nsu.voronova.snakegame.sprite.snake;
    opens ru.nsu.voronova.snakegame.sprite.snake to javafx.fxml;
    exports ru.nsu.voronova.snakegame.sprite.board;
    opens ru.nsu.voronova.snakegame.sprite.board to javafx.fxml;
    exports ru.nsu.voronova.snakegame.sprite.fruit;
    opens ru.nsu.voronova.snakegame.sprite.fruit to javafx.fxml;
    exports ru.nsu.voronova.snakegamefx.sprite.snake;
    opens ru.nsu.voronova.snakegamefx.sprite.snake to javafx.fxml;
    exports ru.nsu.voronova.snakegamefx.sprite.board;
    opens ru.nsu.voronova.snakegamefx.sprite.board to javafx.fxml;
    exports ru.nsu.voronova.snakegamefx.sprite.fruit;
    opens ru.nsu.voronova.snakegamefx.sprite.fruit to javafx.fxml;
    exports ru.nsu.voronova.snakegame.game.state;
    opens ru.nsu.voronova.snakegame.game.state to javafx.fxml;
    exports ru.nsu.voronova.snakegame;
    opens ru.nsu.voronova.snakegame to javafx.fxml;
}
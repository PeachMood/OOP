module ru.nsu.voronova {
    requires javafx.controls;
    requires javafx.fxml;

    exports ru.nsu.voronova;
    opens ru.nsu.voronova to javafx.fxml;
    exports ru.nsu.voronova.snakegame.sprite;
    opens ru.nsu.voronova.snakegame.sprite to javafx.fxml;
    exports ru.nsu.voronova.snakegame.sprite.board;
    opens ru.nsu.voronova.snakegame.sprite.board to javafx.fxml;
    exports ru.nsu.voronova.snakegame.sprite.snake;
    opens ru.nsu.voronova.snakegame.sprite.snake to javafx.fxml;
    exports ru.nsu.voronova.snakegame.sprite.fruit;
    opens ru.nsu.voronova.snakegame.sprite.fruit to javafx.fxml;
    exports ru.nsu.voronova.snakegame.game;
    opens ru.nsu.voronova.snakegame.game to javafx.fxml;
    exports ru.nsu.voronova.snakegame.cell;
    opens ru.nsu.voronova.snakegame.cell to javafx.fxml;
    exports ru.nsu.voronova.snakegamefx.skin;
    opens ru.nsu.voronova.snakegamefx.skin to javafx.fxml;
    exports ru.nsu.voronova.snakegamefx.sprite;
    opens ru.nsu.voronova.snakegamefx.sprite to javafx.fxml;
    exports ru.nsu.voronova.snakegamefx.game;
    opens ru.nsu.voronova.snakegamefx.game to javafx.fxml;
    exports ru.nsu.voronova.application.modalwindow;
    opens ru.nsu.voronova.application.modalwindow to javafx.fxml;
    exports ru.nsu.voronova.application.menu;
    opens ru.nsu.voronova.application.menu to javafx.fxml;
    exports ru.nsu.voronova.application.settings;
    opens ru.nsu.voronova.application.settings to javafx.fxml;
    exports ru.nsu.voronova.application.configuration;
    opens ru.nsu.voronova.application.configuration to javafx.fxml;
    exports ru.nsu.voronova.application.snakegame;
    opens ru.nsu.voronova.application.snakegame to javafx.fxml;
}
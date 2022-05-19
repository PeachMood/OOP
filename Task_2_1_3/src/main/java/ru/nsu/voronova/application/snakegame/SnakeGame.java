package ru.nsu.voronova.application.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.snakegame.sprite.board.Board;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;
import ru.nsu.voronova.snakegame.sprite.wall.Wall;
import ru.nsu.voronova.snakegamefx.game.GameController;
import ru.nsu.voronova.snakegamefx.game.GameFX;
import ru.nsu.voronova.snakegamefx.sprite.BoardFX;
import ru.nsu.voronova.snakegamefx.skin.Skin;
import ru.nsu.voronova.snakegamefx.sprite.FruitFX;
import ru.nsu.voronova.snakegamefx.sprite.SnakeFX;
import ru.nsu.voronova.snakegamefx.sprite.WallFX;

import java.io.IOException;
import java.util.*;

/**
 * This class is responsible for initializing all classes required for the snake game and provides an interface for user interaction.
 */
public class SnakeGame {
    private Stage stage;
    private final Configuration configuration;
    private Map<String, Image> images;
    private Board board;
    private Snake snake;
    private List<Fruit> food;
    private List<Wall> walls;
    private GameFX snakeGame;
    private Group frame;
    private GameController gameController;
    private Scene scene;
    private Timeline timeline;

    /**
     * Class constructor.
     *
     * @param configuration - the current configuration of the game.
     */
    public SnakeGame(Configuration configuration) {
        this.configuration = configuration;
        setImages();
        setSnakeGame();
    }

    private void setImages() {
        this.images = new HashMap<>();
        images.put("board", new Image(String.valueOf(getClass().getResource("/ru/nsu/voronova/images/board/board.png"))));
        images.put("head", new Image(String.valueOf(getClass().getResource("/ru/nsu/voronova/images/snake/head.png"))));
        images.put("rotated", new Image(String.valueOf(getClass().getResource("/ru/nsu/voronova/images/snake/rotated.png"))));
        images.put("straight", new Image(String.valueOf(getClass().getResource("/ru/nsu/voronova/images/snake/straight.png"))));
        images.put("tail", new Image(String.valueOf(getClass().getResource("/ru/nsu/voronova/images/snake/tail.png"))));
        images.put("apple", new Image(String.valueOf(getClass().getResource("/ru/nsu/voronova/images/fruit/apple.png"))));
    }

    private void setBoard() {
        double width = configuration.squareSize() * configuration.rowsNumber();
        double height = configuration.squareSize() * configuration.columnsNumber();
        Skin skin = new Skin(width, height, images.get("board"));
        BoardFX board = new BoardFX(width, height);
        board.setSkin(skin);
        this.board = board;
    }

    private void setSnake() {
        Skin head = new Skin(configuration.squareSize(), configuration.squareSize(), images.get("head"));
        Skin rotated = new Skin(configuration.squareSize(), configuration.squareSize(), images.get("rotated"));
        Skin straight = new Skin(configuration.squareSize(), configuration.squareSize(), images.get("straight"));
        Skin tail = new Skin(configuration.squareSize(), configuration.squareSize(), images.get("tail"));
        SnakeFX snake = new SnakeFX(configuration.squareSize(), configuration.squareSize());
        snake.setSkins(head, rotated, straight, tail);
        this.snake = snake;
    }

    private void setFood() {
        Skin skin = new Skin(configuration.squareSize(), configuration.squareSize(), images.get("apple"));
        List<Fruit> food = new ArrayList<>();
        for (int i = 0; i < configuration.fruitsNumber(); ++i) {
            FruitFX fruit = new FruitFX(configuration.squareSize(), configuration.squareSize());
            fruit.setSkin(skin);
            food.add(fruit);
        }
        this.food = food;
    }

    private void setWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int i = 0; i < configuration.wallsNumber(); ++i) {
            WallFX wall = new WallFX(configuration.squareSize(), configuration.squareSize());
            wall.setColor(Color.web("#bcae76"));
            walls.add(wall);
        }
        this.walls = walls;
    }

    private void setSnakeGame() {
        setBoard();
        setSnake();
        setFood();
        setWalls();
        snakeGame = new GameFX(configuration, board, snake, food, walls);
        snakeGame.start();
    }

    private void setScene() {
        frame = new Group();
        snakeGame.render(frame);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/voronova/fxml/snakeGame.fxml"));
        try {
            BorderPane root = loader.load();
            SnakeGameController snakeGameController = loader.getController();
            snakeGameController.initialize(stage, configuration, timeline, snakeGame);
            root.setCenter(frame);
            scene = new Scene(root);
            gameController = new GameController(snakeGameController, snakeGame, timeline);
            scene.setOnKeyPressed(gameController::handle);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void setTimeline() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(configuration.snakeSpeed()), event -> gameController.run(frame)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Sets snake game in the specified window.
     *
     * @param stage - the specified window.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        setTimeline();
        setScene();
        if (scene != null) {
            stage.setScene(scene);
        }
    }
}

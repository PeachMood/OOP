package ru.nsu.voronova;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.voronova.snakegame.game.SnakeGame;
import ru.nsu.voronova.snakegame.Configuration;
import ru.nsu.voronova.snakegame.sprite.board.Board;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;
import ru.nsu.voronova.snakegamefx.skin.Skin;
import ru.nsu.voronova.snakegamefx.sprite.board.BoardFX;
import ru.nsu.voronova.snakegamefx.sprite.fruit.FruitFX;
import ru.nsu.voronova.snakegamefx.sprite.snake.SnakeFX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.nsu.voronova.snakegame.game.state.GameState.PLAY;

public class SnakeGameFX {
    private Stage stage;
    private final Configuration configuration;
    private Map<String, Image> images;
    private Board board;
    private Snake snake;
    private List<Fruit> food;
    private SnakeGame snakeGame;
    private Group playingField;
    private SnakeGameController snakeGameController;
    private Scene scene;
    private Timeline timeline;

    public SnakeGameFX(Configuration configuration) {
        this.configuration = configuration;
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

    private void setSnakeGame() {
        setBoard();
        setSnake();
        setFood();
        snakeGame = new SnakeGame(configuration, board, snake, food);
        snakeGame.start();
    }

    private void setPlayingField() {
        playingField = new Group();
        playingField.setOnKeyPressed(snakeGameController::onKeyPressed);
        board.render(playingField);
        snake.render(playingField);
        food.forEach(fruit -> fruit.render(playingField));
    }

    private void setController() {
        this.snakeGameController = new SnakeGameController(snakeGame);
    }

    private void setScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/voronova/fxml/snakeGame.fxml"));
        loader.setController(snakeGameController);
        BorderPane root = loader.load();
        root.setCenter(playingField);
        scene = new Scene(root);
        scene.setOnKeyPressed(snakeGameController::onKeyPressed);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/ru/nsu/voronova/css/styles.css")));
    }

    private void setTimeline() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(configuration.snakeSpeed()), e -> {
            snakeGameController.setScore(snakeGame.getScore());
            snakeGame.run();
            if (snakeGame.getGameState() == PLAY) {
                playingField.getChildren().clear();
                board.render(playingField);
                food.forEach(fruit -> fruit.render(playingField));
                snake.render(playingField);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        setImages();
        setSnakeGame();
        setController();
        setPlayingField();
        setTimeline();
        try {
            setScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
    }
}

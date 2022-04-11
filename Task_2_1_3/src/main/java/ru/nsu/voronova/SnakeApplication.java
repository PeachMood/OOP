package ru.nsu.voronova;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.voronova.skin.SnakeSkin;
import ru.nsu.voronova.snake.Snake;

import static ru.nsu.voronova.direction.Direction.*;

public class SnakeApplication extends Application {
    private Snake snake;
    private SnakeSkin snakeSkin;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        snakeSkin = new SnakeSkin(40, 40);
        snakeSkin.setHeadSkin(new Image(String.valueOf(getClass().getResource("images/head.png"))));
        snakeSkin.setRotatedBodySkin(new Image(String.valueOf(getClass().getResource("images/rotated.png"))));
        snakeSkin.setStraightBodySkin(new Image(String.valueOf(getClass().getResource("images/straight.png"))));
        snakeSkin.setTailSkin(new Image(String.valueOf(getClass().getResource("images/tail.png"))));
        snake = new Snake(snakeSkin);
        snake.initializeSnake(5, 120, 120);

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.RIGHT || code == KeyCode.D) {
                snake.setDirection(RIGHT);
            } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                snake.setDirection(LEFT);
            } else if (code == KeyCode.UP || code == KeyCode.W) {
                snake.setDirection(UP);
            } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                snake.setDirection(DOWN);
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> run(root)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void run(Group group) {
        group.getChildren().clear();
        group.getChildren().addAll(snake.run());
    }

    public static void main(String[] args) {
        launch();
    }
}

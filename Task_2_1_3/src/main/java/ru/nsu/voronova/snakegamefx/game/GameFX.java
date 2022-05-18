package ru.nsu.voronova.snakegamefx.game;

import javafx.scene.Group;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.snakegame.game.Game;
import ru.nsu.voronova.snakegame.sprite.board.Board;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;

import java.util.List;

import static ru.nsu.voronova.snakegame.game.GameState.PLAY;

public class GameFX extends Game {
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;

    public GameFX(Configuration configuration, Board board, Snake snake, List<Fruit> food) {
        super(configuration, board, snake, food);
        this.board = board;
        this.snake = snake;
        this.food = food;
    }

    public void render(Group frame) {
        if (getGameState() == PLAY) {
            frame.getChildren().clear();
            board.render(frame);
            food.forEach(fruit -> fruit.render(frame));
            snake.render(frame);
        }
    }
}

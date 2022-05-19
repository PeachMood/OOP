package ru.nsu.voronova.snakegamefx.game;

import javafx.scene.Group;
import ru.nsu.voronova.application.configuration.Configuration;
import ru.nsu.voronova.snakegame.game.Game;
import ru.nsu.voronova.snakegame.sprite.board.Board;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;
import ru.nsu.voronova.snakegame.sprite.wall.Wall;

import java.util.List;

import static ru.nsu.voronova.snakegame.game.GameState.PLAY;

public class GameFX extends Game {
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;
    private final List<Wall> walls;

    public GameFX(Configuration configuration, Board board, Snake snake, List<Fruit> food, List<Wall> walls) {
        super(configuration, board, snake, food, walls);
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.walls = walls;
    }

    public void render(Group frame) {
        if (getGameState() == PLAY) {
            frame.getChildren().clear();
            board.render(frame);
            walls.forEach(wall -> wall.render(frame));
            food.forEach(fruit -> fruit.render(frame));
            snake.render(frame);
        }
    }
}

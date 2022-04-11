package ru.nsu.voronova.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import ru.nsu.voronova.direction.Direction;
import ru.nsu.voronova.skin.SnakeSkin;
import ru.nsu.voronova.sprite.Flake;
import ru.nsu.voronova.sprite.Head;
import ru.nsu.voronova.sprite.Sprite;
import ru.nsu.voronova.sprite.Tail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ru.nsu.voronova.direction.Direction.*;

public class Snake {
    private final SnakeSkin snakeSkin;
    private int snakeLength;
    private Direction direction;
    private final Head head;
    private final Tail tail;
    private final List<Sprite> body;

    public Snake(SnakeSkin snakeSkin) {
        this.snakeSkin = snakeSkin;
        this.head = new Head(snakeSkin.getHeadSkin());
        this.tail = new Tail(snakeSkin.getTailSkin());
        this.body = new ArrayList<>();
    }

    public void initializeSnake(int snakeLength, double headPositionX, double headPositionY) {
        this.snakeLength = snakeLength;
        setDirection(RIGHT);
        head.setPosition(headPositionX, headPositionY);
        body.add(head);
        body.addAll(Stream.generate(() -> new Flake(snakeSkin.getRotatedBodySkin(), snakeSkin.getStraightBodySkin())).limit(snakeLength - 2).toList());
        body.add(tail);
        for (int i = snakeLength - 1; i >= 1; --i) {
            body.get(i).setPosition(body.get(i - 1).getPositionX() - snakeSkin.getWidth(), headPositionY);
        }
        updateSnakeImage();
    }

    public void setDirection(Direction direction) {
        if ((this.direction == UP && direction == DOWN) || (this.direction == DOWN && direction == UP)) {
            return;
        }
        if ((this.direction == LEFT && direction == RIGHT) || (this.direction == RIGHT && direction == LEFT)) {
            return;
        }
        this.direction = direction;
    }

    private void updateSnakePosition() {
        double stepX = snakeSkin.getWidth();
        double stepY = snakeSkin.getHeight();
        for (int i = snakeLength - 1; i >= 1; --i) {
            body.get(i).setPosition(body.get(i - 1).getPositionX(), body.get(i - 1).getPositionY());
        }
        switch (direction) {
            case RIGHT -> head.setPosition(head.getPositionX() + stepX, head.getPositionY());
            case LEFT -> head.setPosition(head.getPositionX() - stepX, head.getPositionY());
            case UP -> head.setPosition(head.getPositionX(), head.getPositionY() - stepY);
            case DOWN -> head.setPosition(head.getPositionX(), head.getPositionY() + stepY);
        }
    }

    private void updateSnakeImage() {
        head.updateHeadImage(direction);
        for (int i = 1; i < snakeLength - 1; ++i) {
            Flake flake = (Flake) body.get(i);
            flake.updateBodyImage(body.get(i + 1), body.get(i - 1));
        }
        tail.updateTailImage(body.get(snakeLength - 2));
    }

    public List<ImageView> getSnakeImage() {
        List<ImageView> snakeImage = new ArrayList<>();
        body.forEach(flake -> snakeImage.add(flake.getImageView()));
        return snakeImage;
    }

    public boolean intersects(Sprite sprite) {
        return head.intersects(sprite);
    }

    public List<ImageView> run() {
        updateSnakePosition();
        updateSnakeImage();
        return getSnakeImage();
    }
}

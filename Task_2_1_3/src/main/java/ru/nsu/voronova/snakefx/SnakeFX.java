package ru.nsu.voronova.snakefx;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.voronova.snakefx.skin.SnakeSkin;
import ru.nsu.voronova.direction.Direction;
import ru.nsu.voronova.snake.Snake;
import ru.nsu.voronova.snake.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

public class SnakeFX extends Snake {
    private SnakeSkin snakeSkin;

    public SnakeFX(int length, double width, double height) {
        super(length, width, height);
    }

    public void setSnakeSkin(SnakeSkin snakeSkin) {
        this.snakeSkin = snakeSkin;
    }

    private ImageView renderSprite(Sprite sprite, ImageView imageView) {
        imageView.setX(sprite.getPositionX());
        imageView.setY(sprite.getPositionY());
        return imageView;
    }

    private ImageView renderHead(Sprite head, Direction direction) {
        ImageView imageView = switch (direction) {
            case LEFT -> snakeSkin.head().getRotatedImage(180);
            case UP -> snakeSkin.head().getRotatedImage(270);
            case DOWN -> snakeSkin.head().getRotatedImage(90);
            case RIGHT -> snakeSkin.head().getImage();
        };
        return renderSprite(head, imageView);
    }

    private ImageView renderTail(Sprite tail, Sprite previous) {
        if (tail.getPositionY() == previous.getPositionY()) {
            if (tail.getPositionX() < previous.getPositionX()) {
                return renderSprite(tail, snakeSkin.tail().getImage());
            }
            if (tail.getPositionX() > previous.getPositionX()) {
                return renderSprite(tail, snakeSkin.tail().getRotatedImage(180));
            }
        }
        if (tail.getPositionY() > previous.getPositionY()) {
            return renderSprite(tail, snakeSkin.tail().getRotatedImage(270));
        }
        return renderSprite(tail, snakeSkin.tail().getRotatedImage(90));
    }

    private ImageView renderFlake(Sprite flake, Sprite previous, Sprite next) {
        if (next.getPositionY() == previous.getPositionY()) {
            return renderSprite(flake, snakeSkin.straightBody().getImage());
        }
        if (flake.getPositionX() == previous.getPositionX()) {
            if (next.getPositionY() < previous.getPositionY()) {
                if (next.getPositionX() < previous.getPositionX()) {
                    return renderSprite(flake, snakeSkin.rotatedBody().getImage());
                }
                if (next.getPositionX() > previous.getPositionX()) {
                    return renderSprite(flake, snakeSkin.rotatedBody().getRotatedImage(270));
                }
            }
            if (next.getPositionY() > previous.getPositionY()) {
                if (next.getPositionX() < previous.getPositionX()) {
                    return renderSprite(flake, snakeSkin.rotatedBody().getRotatedImage(90));
                }
                if (next.getPositionX() > previous.getPositionX()) {
                    return renderSprite(flake, snakeSkin.rotatedBody().getRotatedImage(180));
                }
            }
        }
        if (flake.getPositionX() == next.getPositionX()) {
            if (next.getPositionY() < previous.getPositionY()) {
                if (next.getPositionX() < previous.getPositionX()) {
                    return renderSprite(flake, snakeSkin.rotatedBody().getRotatedImage(180));
                }
                if (next.getPositionX() > previous.getPositionX()) {
                    return renderSprite(flake, snakeSkin.rotatedBody().getRotatedImage(90));
                }
            }
            if (next.getPositionY() > previous.getPositionY()) {
                if (next.getPositionX() < previous.getPositionX()) {
                    return renderSprite(flake, snakeSkin.rotatedBody().getRotatedImage(270));
                }
                if (next.getPositionX() > previous.getPositionX()) {
                    return renderSprite(flake, snakeSkin.rotatedBody().getImage());
                }
            }
        }
        return renderSprite(flake, snakeSkin.straightBody().getRotatedImage(90));
    }

    private List<ImageView> renderBody(int length, List<Sprite> body) {
        List<ImageView> renderedBody = new ArrayList<>();
        renderedBody.add(renderHead(body.get(0), getDirection()));
        for (int i = 1; i < length - 1; ++i) {
            renderedBody.add(renderFlake(body.get(i), body.get(i + 1), body.get(i - 1)));
        }
        renderedBody.add(renderTail(body.get(length - 1), body.get(length - 2)));
        return renderedBody;
    }

    @Override
    public Group render() {
        Group snake = new Group();
        snake.getChildren().addAll(renderBody(getLength(), getBody()));
        return snake;
    }
}

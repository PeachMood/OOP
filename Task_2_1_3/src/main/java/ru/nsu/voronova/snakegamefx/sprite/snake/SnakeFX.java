package ru.nsu.voronova.snakegamefx.sprite.snake;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.snake.Direction;
import ru.nsu.voronova.snakegame.sprite.snake.Snake;
import ru.nsu.voronova.snakegamefx.skin.Skin;

import java.util.ArrayList;
import java.util.List;

public class SnakeFX extends Snake {
    private Skin headSkin;
    private Skin rotatedSkin;
    private Skin straightSkin;
    private Skin tailSkin;

    public SnakeFX(double width, double height) {
        super(width, height);
    }

    public void setSkins(Skin headSkin, Skin rotatedSkin, Skin straightSkin, Skin tailSkin) {
        this.headSkin = headSkin;
        this.rotatedSkin = rotatedSkin;
        this.straightSkin = straightSkin;
        this.tailSkin = tailSkin;
    }

    private ImageView renderCell(Cell cell, ImageView imageView) {
        imageView.setX(cell.getPositionX());
        imageView.setY(cell.getPositionY());
        return imageView;
    }

    private ImageView renderHead(Cell head, Direction direction) {
        ImageView imageView = switch (direction) {
            case LEFT -> headSkin.getRotatedImage(180);
            case UP -> headSkin.getRotatedImage(270);
            case DOWN -> headSkin.getRotatedImage(90);
            case RIGHT -> headSkin.getImage();
        };
        return renderCell(head, imageView);
    }

    private ImageView renderTail(Cell tail, Cell previous) {
        if (tail.getPositionY() == previous.getPositionY()) {
            if (tail.getPositionX() < previous.getPositionX()) {
                return renderCell(tail, tailSkin.getImage());
            }
            if (tail.getPositionX() > previous.getPositionX()) {
                return renderCell(tail, tailSkin.getRotatedImage(180));
            }
        }
        if (tail.getPositionY() > previous.getPositionY()) {
            return renderCell(tail, tailSkin.getRotatedImage(270));
        }
        return renderCell(tail, tailSkin.getRotatedImage(90));
    }

    private ImageView renderFlake(Cell flake, Cell previous, Cell next) {
        if (next.getPositionY() == previous.getPositionY()) {
            return renderCell(flake, straightSkin.getImage());
        }
        if (flake.getPositionX() == previous.getPositionX()) {
            if (next.getPositionY() < previous.getPositionY()) {
                if (next.getPositionX() < previous.getPositionX()) {
                    return renderCell(flake, rotatedSkin.getImage());
                }
                if (next.getPositionX() > previous.getPositionX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(270));
                }
            }
            if (next.getPositionY() > previous.getPositionY()) {
                if (next.getPositionX() < previous.getPositionX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(90));
                }
                if (next.getPositionX() > previous.getPositionX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(180));
                }
            }
        }
        if (flake.getPositionX() == next.getPositionX()) {
            if (next.getPositionY() < previous.getPositionY()) {
                if (next.getPositionX() < previous.getPositionX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(180));
                }
                if (next.getPositionX() > previous.getPositionX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(90));
                }
            }
            if (next.getPositionY() > previous.getPositionY()) {
                if (next.getPositionX() < previous.getPositionX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(270));
                }
                if (next.getPositionX() > previous.getPositionX()) {
                    return renderCell(flake, rotatedSkin.getImage());
                }
            }
        }
        return renderCell(flake, straightSkin.getRotatedImage(90));
    }

    private List<ImageView> renderBody(int length, Cell[] body) {
        List<ImageView> renderedBody = new ArrayList<>();
        renderedBody.add(renderHead(body[0], getDirection()));
        for (int i = 1; i < length - 1; ++i) {
            renderedBody.add(renderFlake(body[i], body[i + 1], body[i - 1]));
        }
        renderedBody.add(renderTail(body[length - 1], body[length - 2]));
        return renderedBody;
    }

    @Override
    public void render(Object object) {
        Group playingField = ((Group) object);
        Group snake = new Group();
        snake.getChildren().addAll(renderBody(getLength(), getBoundary()));
        playingField.getChildren().add(snake);
    }
}

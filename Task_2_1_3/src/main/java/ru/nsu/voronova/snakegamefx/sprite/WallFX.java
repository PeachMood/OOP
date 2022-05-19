package ru.nsu.voronova.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.wall.Wall;

public class WallFX extends Wall {
    private final Rectangle rectangle;

    public WallFX(double width, double height) {
        super(width, height);
        rectangle = new Rectangle(width, height);
    }

    public void setColor(Paint color) {
        rectangle.setFill(color);
    }

    public Rectangle renderCell(Cell cell, Rectangle rectangle) {
        rectangle.setX(cell.getX());
        rectangle.setY(cell.getY());
        return rectangle;
    }

@Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group fruit = new Group();
        fruit.getChildren().add(renderCell(getBoundary(), rectangle));
        frame.getChildren().add(fruit);
    }
}

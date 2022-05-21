package ru.nsu.voronova.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegamefx.skin.Skin;

/**
 * Implementation of the abstract class Fruit, which provides an interface
 * for rendering a fruit on the specified frame depending on fruit coordinates and bounds.
 */
public class FruitFX extends Fruit {
    private Skin skin;

    /**
     * Class constructor. Creates a fruit with specified width and height.
     *
     * @param width  - fruit width.
     * @param height - fruit height.
     */
    public FruitFX(double width, double height) {
        super(width, height);
    }

    /**
     * Changes fruit skin used for its rendering.
     *
     * @param skin - fruit skin.
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    private ImageView renderFruit(Cell fruit, ImageView imageView) {
        imageView.setX(fruit.getX());
        imageView.setY(fruit.getY());
        return imageView;
    }

    /**
     * Renders fruit on the specified frame depending on its coordinates and bounds.
     *
     * @param object - object on which fruit should be rendered.
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group fruit = new Group();
        fruit.getChildren().add(renderFruit(getBoundary(), skin.getImage()));
        frame.getChildren().add(fruit);
    }
}

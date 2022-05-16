package ru.nsu.voronova.snakegamefx.sprite.fruit;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.fruit.Fruit;
import ru.nsu.voronova.snakegamefx.skin.Skin;

public class FruitFX extends Fruit {
    private Skin skin;

    public FruitFX(double width, double height) {
        super(width, height);
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    private ImageView renderFruit(Cell fruit, ImageView imageView) {
        imageView.setX(fruit.getPositionX());
        imageView.setY(fruit.getPositionY());
        return imageView;
    }

    @Override
    public void render(Object object) {
        Group playingField = ((Group) object);
        Group fruit = new Group();
        fruit.getChildren().add(renderFruit(getBoundary(), skin.getImage()));
        playingField.getChildren().add(fruit);
    }
}

package ru.nsu.voronova.sprite;

import ru.nsu.voronova.skin.Skin;
import ru.nsu.voronova.sprite.Sprite;

public class Tail extends Sprite {
    private final Skin tail;

    public Tail(Skin tail) {
        this.tail = tail;
    }

    public void updateTailImage(Sprite previousFlake) {
        if (getPositionY() == previousFlake.getPositionY()) {
            setImage(tail.getSkin());
            return;
        }
        setImage(tail.getRotatedSkin(90));
    }
}

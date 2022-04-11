package ru.nsu.voronova.sprite;

import ru.nsu.voronova.skin.Skin;
import ru.nsu.voronova.sprite.Sprite;

public class Flake extends Sprite {
    private final Skin rotated;
    private final Skin straight;

    public Flake(Skin rotated, Skin straight) {
        this.rotated = rotated;
        this.straight = straight;
    }

    public void updateBodyImage(Sprite previous, Sprite next) {
        if (next.getPositionY() == previous.getPositionY()) {
            setImage(straight.getSkin());
            return;
        }
        if (next.getPositionX() == previous.getPositionX()) {
            setImage(straight.getRotatedSkin(90));
            return;
        }
        if (next.getPositionX() > previous.getPositionX()) {
            if (next.getPositionY() > previous.getPositionY()) {
                setImage(rotated.getSkin());
                return;
            }
            if (next.getPositionY() < previous.getPositionY()) {
                setImage(rotated.getRotatedSkin(90));
                return;
            }
        }
        if (next.getPositionX() < previous.getPositionX()) {
            if (next.getPositionY() > previous.getPositionY()) {
                setImage(rotated.getRotatedSkin(180));
                return;
            }
            if (next.getPositionY() < previous.getPositionY()) {
                setImage(rotated.getRotatedSkin(270));
            }
        }
    }
}

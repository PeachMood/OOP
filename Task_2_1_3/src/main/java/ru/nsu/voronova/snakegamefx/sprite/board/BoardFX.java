package ru.nsu.voronova.snakegamefx.sprite.board;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.board.Board;
import ru.nsu.voronova.snakegamefx.skin.Skin;

public class BoardFX extends Board {
    private Skin skin;

    public BoardFX(double width, double height) {
        super(width, height);
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    private ImageView renderBoard(Cell board, ImageView imageView) {
        imageView.setX(board.getPositionX());
        imageView.setY(board.getPositionY());
        return imageView;
    }

    @Override
    public void render(Object object) {
        Group playingField = ((Group) object);
        Group board = new Group();
        board.getChildren().add(renderBoard(getBoundary(), skin.getImage()));
        playingField.getChildren().add(board);
    }
}
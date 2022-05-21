package ru.nsu.voronova.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.voronova.snakegamefx.skin.Skin;
import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.board.Board;

/**
 * Implementation of the abstract class Board.
 * Implements the {@link #render(Object)} method for the board image on the specified frame.
 */
public class BoardFX extends Board {
    private Skin skin;

    /**
     * Class constructor. Creates a board with specified width and height.
     *
     * @param width  - board width.
     * @param height - board height.
     */
    public BoardFX(double width, double height) {
        super(width, height);
    }

    /**
     * Changes board skin used for its rendering.
     *
     * @param skin - new board skin.
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    private ImageView renderBoard(Cell board, ImageView imageView) {
        imageView.setX(board.getX());
        imageView.setY(board.getY());
        return imageView;
    }

    /**
     * Renders board on the specified frame.
     *
     * @param object - object on which board should be rendered.
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group board = new Group();
        board.getChildren().add(renderBoard(getBoundary(), skin.getImage()));
        frame.getChildren().add(board);
    }
}

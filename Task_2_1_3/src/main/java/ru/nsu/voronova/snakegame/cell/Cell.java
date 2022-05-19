package ru.nsu.voronova.snakegame.cell;

/**
 * This class is a rectangle with the specified height, width and coordinates
 */
public class Cell {
    private final double width;
    private final double height;
    private double x;
    private double y;

    /**
     * Class constructor.
     *
     * @param width  - width of the cell.
     * @param height - height of the cell.
     */
    public Cell(double width, double height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Returns cell width.
     *
     * @return cell width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns cell height.
     *
     * @return cell height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns cell x coordinate.
     *
     * @return cell x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns cell y coordinate.
     *
     * @return cell y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Changes cell's position.
     *
     * @param x - new x coordinate of the cell.
     * @param y - new y coordinate of the cell.
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * It is determined whether the coordinates of a given cell intersect with another.
     *
     * @param cell - another cell.
     * @return true if the coordinates of a given cell intersect with the specified one.
     */
    public boolean intersects(Cell cell) {
        return cell.x + cell.width > x && cell.y + cell.height > y && cell.x < x + width && cell.y < y + height;
    }

    /**
     * Compare the cell with other objects
     *
     * @param object - the specified object.
     * @return true if the specified object is the current cell or coordinates of specified cells are equal.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Cell cell) {
            return cell.x == x && cell.y == y && cell.x + cell.width == x + width && cell.y + cell.height == y + height;
        }
        return false;
    }
}


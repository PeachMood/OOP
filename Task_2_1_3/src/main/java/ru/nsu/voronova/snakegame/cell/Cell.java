package ru.nsu.voronova.snakegame.cell;


public class Cell {
    private final double width;
    private final double height;
    private double positionX;
    private double positionY;

    public Cell(double width, double height) {
        this.width = width;
        this.height = height;
        this.positionX = 0;
        this.positionY = 0;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public boolean intersects(Cell cell) {
        double thisWidth = width;
        double thisHeight = this.height;
        double otherWidth = cell.width;
        double otherHeight = cell.height;
        otherWidth += cell.positionX;
        otherHeight += cell.positionY;
        thisWidth += positionX;
        thisHeight += positionY;
        return ((otherWidth < cell.positionX || otherWidth > positionX) &&
                (otherHeight < cell.positionY || otherHeight > positionY) &&
                (thisWidth < positionX || thisWidth > cell.positionX) &&
                (thisHeight < positionY || thisHeight > cell.positionY));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Cell clone() {
        Cell clone = new Cell(width, height);
        clone.setPosition(positionX, positionY);
        return clone;
    }
}


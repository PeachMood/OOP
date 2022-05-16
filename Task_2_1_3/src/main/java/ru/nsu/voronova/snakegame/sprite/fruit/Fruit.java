package ru.nsu.voronova.snakegame.sprite.fruit;

import ru.nsu.voronova.snakegame.cell.Cell;
import ru.nsu.voronova.snakegame.sprite.Sprite;

import java.util.Arrays;
import java.util.Random;


public abstract class Fruit implements Sprite {
    private final Cell fruit;
    private final Random random;

    public Fruit(double width, double height) {
        this.fruit = new Cell(width, height);
        this.random = new Random();
    }

    @Override
    public Cell getBoundary() {
        return fruit.clone();
    }

    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite.getBoundary() instanceof Cell cell) {
            return fruit != cell && fruit.intersects(cell);
        }
        if (sprite.getBoundary() instanceof Cell[] boundary) {
            return Arrays.stream(boundary).anyMatch(cell -> fruit != cell && fruit.intersects(cell));
        }
        return false;
    }


    @Override
    public void update(double rowsNumber, double columnsNumber) {
        double foodX = random.nextInt((int) rowsNumber - 1);
        double foodY = random.nextInt((int) columnsNumber - 1);
        fruit.setPosition(foodX * fruit.getWidth(), foodY * fruit.getHeight());
    }

    @Override
    public abstract void render(Object object);
}

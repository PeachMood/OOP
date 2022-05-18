package ru.nsu.voronova.snakegame.game;

public enum GameState {
    DEFEAT("GAME OVER"),
    VICTORY("YOU WIN"),
    PLAY("SNAKE GAME");

    private final String state;

    GameState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}

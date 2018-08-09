package com.nibble.wheelofjeopardy.game;

public class GameManager {

    private static GameManager instance = null;
    private static Game currentGame = null;
    private static boolean gameInProgress = false;

    private GameManager() {}

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public static void newGame(Game game) {
        currentGame = game;
        gameInProgress = true;
    }

    public static Game getGame() {
        return currentGame;
    }

    public static void endGame() {
        currentGame = null;
        gameInProgress = false;
    }
}

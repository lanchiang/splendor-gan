package com.github.lanchiang.game;

import com.github.lanchiang.conclusions.ConclusionBoard;

/**
 * A Game box contains all the game records produced during its execution.
 *
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class GameBox {

    private final ConclusionBoard conclusionBoard;

    public GameBox() {
        conclusionBoard = new ConclusionBoard();
    }

    public void run() {
        while (true) {
            Game game = new Game(2);
            game.run();
            conclusionBoard.concludeGame(game);
        }
    }

    public static void main(String[] args) {
        GameBox gameBox = new GameBox();
        gameBox.run();
    }
}

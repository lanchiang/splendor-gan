package com.github.lanchiang.game;

import com.github.lanchiang.conclusions.ConclusionBoard;
import com.github.lanchiang.exceptions.ActionNotExecutableException;

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

    public void run(int numOfPlayer) throws ActionNotExecutableException {
        while (true) {
            Game game = new Game(numOfPlayer);
            game.run();
            conclusionBoard.concludeGame(game);
        }
    }

    public static void main(String[] args) {
        GameBox gameBox = new GameBox();
        try {
            gameBox.run(2);
        } catch (ActionNotExecutableException e) {
            e.printStackTrace();
        }
    }
}

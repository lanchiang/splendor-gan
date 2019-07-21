package com.github.lanchiang.conclusions;

import com.github.lanchiang.game.Game;

import java.util.LinkedList;
import java.util.List;

/**
 * This is the scoreboard for all the games ever recorded.
 *
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class ConclusionBoard {

    private final List<Conclusion> conclusions;

    public ConclusionBoard() {
        conclusions = new LinkedList<>();
    }

    /**
     * Conclude a game and add the conclusion into the board.
     * @param game the game to be concluded.
     */
    public void concludeGame(Game game) {
        Conclusion conclusion = new Conclusion(game);
        conclusions.add(conclusion);
    }
}

package com.github.lanchiang.game;

import com.github.lanchiang.Player;
import com.github.lanchiang.elements.DevelopmentCardPool;
import com.github.lanchiang.elements.GemstonePool;
import com.github.lanchiang.elements.NobleTiles;
import lombok.Getter;

/**
 * This class represent one game.
 *
 * @author Lan Jiang
 * @since 2019-07-20
 */
public class Game {

    private int numOfPlayers;

    private Player[] players;

    @Getter
    private NobleTiles nobleTiles;

    @Getter
    private DevelopmentCardPool developmentCardPool;

    @Getter
    private GemstonePool gemstonePool;

    public Game(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    private void initGameElements() {
        developmentCardPool = DevelopmentCardPool.getInstance();
        nobleTiles = NobleTiles.getInstance();
        gemstonePool = GemstonePool.getInstance(numOfPlayers);

        players = new Player[numOfPlayers];
    }

    /**
     * @return true if the game should stop, i.e., a player has reached 15 prestige points.
     */
    private boolean shouldFinish() {
        return false;
    }
}

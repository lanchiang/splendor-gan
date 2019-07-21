package com.github.lanchiang.game;

import com.github.lanchiang.components.DevelopmentCardPool;
import com.github.lanchiang.components.GemstonePool;
import com.github.lanchiang.components.NobleTiles;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

/**
 * This class represent one game.
 *
 * @author Lan Jiang
 * @since 2019-07-20
 */
public class Game {

    /**
     * Number of players in this game. It can be only between 2 and 4, inclusively.
     */
    private int numOfPlayers;

    private Player[] players;

    @Getter
    private NobleTiles nobleTiles;

    @Getter
    private DevelopmentCardPool developmentCardPool;

    @Getter
    private GemstonePool gemstonePool;

    public Game(int numOfPlayers) {
        Validate.inclusiveBetween(2, 4, numOfPlayers);
        this.numOfPlayers = numOfPlayers;
    }

    /**
     * Run the game.
     */
    public void run() {

    }

    private void initGameElements() {
        developmentCardPool = DevelopmentCardPool.getInstance();
        this.developmentCardPool.initDisplay();

        nobleTiles = NobleTiles.getInstance();
        gemstonePool = GemstonePool.getInstance(numOfPlayers);

        players = new Player[numOfPlayers];
    }

    /**
     * The game finishes if one of the following conditions are fulfilled:
     * 1) a player has reached 15 prestige points;
     * 2) all the cards are obtained by players.
     *
     * @return true if a termination condition is fulfilled.
     */
    private boolean shouldFinish() {
        return false;
    }
}

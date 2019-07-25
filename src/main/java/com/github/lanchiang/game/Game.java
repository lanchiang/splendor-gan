package com.github.lanchiang.game;

import com.github.lanchiang.actions.PlayerAction;
import com.github.lanchiang.components.DevelopmentCardPool;
import com.github.lanchiang.components.GemstonePool;
import com.github.lanchiang.components.NobleTile;
import com.github.lanchiang.components.NobleTiles;
import com.github.lanchiang.exceptions.ActionNotExecutableException;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
    private final int numOfPlayers;

    @Getter
    private Player[] players;

    @Getter
    private Collection<NobleTile> displayedNobleTiles;

    @Getter
    private DevelopmentCardPool developmentCardPool;

    @Getter
    private GemstonePool gemstonePool;

    /**
     * Player action records of this game.
     */
    @Getter
    private List<PlayerAction> playerActions;

    /**
     * the token indicates which player is executing his/her round now.
     */
    private int token = 0;

    public Game(int numOfPlayers) {
        Validate.inclusiveBetween(2, 4, numOfPlayers);
        this.numOfPlayers = numOfPlayers;

        playerActions = new LinkedList<>();

        initGameElements();
    }

    /**
     * Run the game.
     */
    public void run() throws ActionNotExecutableException {
        while (!shouldFinish()) {
            // this player executes an action.
            players[token].perform();

            token = (token + 1) % numOfPlayers; // token goes around.
        }

        // do the finishing logic

        // conclude the game
    }

    private void initGameElements() {
        developmentCardPool = DevelopmentCardPool.getInstance();
        developmentCardPool.initDisplay();

        displayedNobleTiles = displayNobleTiles();
        gemstonePool = GemstonePool.getInstance(numOfPlayers);

        players = new Player[numOfPlayers];
        for (int i=0; i< players.length; i++) {
            players[i] = new Player(this);
        }
    }

    private Collection<NobleTile> displayNobleTiles() {
        int numOfNobleTiles;
        if (numOfPlayers == 2) {
            numOfNobleTiles = 3;
        } else if (numOfPlayers == 3 || numOfPlayers == 4) {
            numOfNobleTiles = 4;
        } else {
            throw new IllegalArgumentException("Number of players is incorrect.");
        }
        return NobleTiles.getInstance().takeRandom(numOfNobleTiles);
    }

    public Player getCurrentPlayer() {
        return players[token];
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

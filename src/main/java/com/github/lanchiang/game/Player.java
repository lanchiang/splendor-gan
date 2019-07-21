package com.github.lanchiang.game;

import com.github.lanchiang.components.Gemstone;

import java.util.HashMap;
import java.util.Map;

/**
 * The player class.
 *
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class Player {

    private Map<Gemstone, Integer> occupied;

    /**
     * Current prestige points owned by this player.
     */
    private int prestigePoints = 0;

    public Player() {
        occupied = new HashMap<>();
        occupied.putIfAbsent(Gemstone.Emerald, 0);
        occupied.putIfAbsent(Gemstone.Diamond, 0);
        occupied.putIfAbsent(Gemstone.Sapphire, 0);
        occupied.putIfAbsent(Gemstone.Onyx, 0);
        occupied.putIfAbsent(Gemstone.Ruby, 0);
    }

    /**
     * The player performs an action according to the current game situation.
     *
     * @param game the game instance
     */
    public void perform(Game game) {

    }

    /**
     *
     */
    synchronized public void acquireGemstones() {
    }

    /**
     *
     */
    synchronized public void acquireDevelopmentCard() {

    }

    /**
     *
     */
    synchronized public void reserveDevelopmentCard() {

    }

    /**
     *
     */
    synchronized public void acquireNobleTile() {

    }
}

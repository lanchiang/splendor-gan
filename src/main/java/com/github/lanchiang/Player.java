package com.github.lanchiang;

import com.github.lanchiang.elements.Gemstone;

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

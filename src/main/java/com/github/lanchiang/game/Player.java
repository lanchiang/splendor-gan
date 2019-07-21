package com.github.lanchiang.game;

import com.github.lanchiang.components.Gemstone;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

import java.util.LinkedList;
import java.util.List;

/**
 * The player class.
 *
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class Player {

    private List<Gemstone> occupied;

    /**
     * Current prestige points owned by this player.
     */
    @Getter
    private int prestigePoints = 0;

    public Player() {
        occupied = new LinkedList<>();
    }

    /**
     * The player performs an action according to the current game situation. There are four optional actions:
     * 1) obtain gemstones: a player can obtain three gemstones all in different colors, or two in the same color if there are greater or equal to four remain.
     *      a player may only keep at maximum 10 gemstones in hand.
     * 2) reserve a card: a player can reserve a card in the displayed cards and take a gold joker if available.
     *      A player cannot have more than three reserved cards at the same time.
     * 3) obtain a card from the displayed cards if the player has enough gemstone resources.
     * 4) play out a reserved card if the player has enough gemstone resources.
     *
     * EXTRA: at the end of a player's turn, he/she may get a noble tile if having enough gemstone cards for it.
     *      A player may get only one noble tile per turn, even if multiple ones are available for him/her. (Note: this is not considered as an action)
     *
     * @param game the game instance
     */
    public void perform(Game game) {

    }

    /**
     * EXTRA: obtain a noble tile.
     */
    synchronized public void obtainNobleTile() {

    }
}

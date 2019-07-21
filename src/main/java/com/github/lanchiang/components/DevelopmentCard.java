package com.github.lanchiang.components;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class DevelopmentCard {

    @Getter
    private final int cardLevel;

    @Getter
    private final Map<Gemstone, Integer> costs;

    /**
     * The prestige points provided by this development card.
     */
    @Getter private final int prestigePoints;

    /**
     * The benefit given by owning this {@link Gemstone}.
     */
    @Getter private final Gemstone benefit;

    /**
     * The state of this card.
     */
    @Getter @Setter
    private CardState state;

    public DevelopmentCard(int cardLevel, int prestigePoints, Gemstone benefit,
                           int emeraldCost, int diamondCost, int sapphireCost, int onyxCost, int rubyCost) {
        this.cardLevel = cardLevel;
        this.prestigePoints = prestigePoints;
        this.benefit = benefit;

        this.costs = new HashMap<>();
        this.costs.putIfAbsent(Gemstone.Emerald, emeraldCost);
        this.costs.putIfAbsent(Gemstone.Diamond, diamondCost);
        this.costs.putIfAbsent(Gemstone.Sapphire, sapphireCost);
        this.costs.putIfAbsent(Gemstone.Onyx, onyxCost);
        this.costs.putIfAbsent(Gemstone.Ruby, rubyCost);

        this.state = CardState.Undisplayed;
    }

    /**
     * The current state of a development card.
     */
    public enum CardState {
        /**
         * The card is still face-down on the board.
         */
        Undisplayed,

        /**
         * The cards is face-up on the board
         */
        Displayed,

        /**
         * The card is in a player's hands.
         */
        Inhand,
    }
}

package com.github.lanchiang.elements;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class DevelopmentCard {

    @Getter
    private int cardLevel;

    @Getter
    private Map<Gemstone, Integer> costs;

    /**
     * The prestige points provided by this development card.
     */
    @Getter private int prestigePoints;

    /**
     * The benefit given by owning this {@link Gemstone}
     */
    @Getter private Gemstone benefit;

    public DevelopmentCard(int cardLevel, int prestigePoints, Gemstone benefit,
                           int emeraldCost, int diamondCost, int sapphireCost, int onyxCost, int rubyCost) {
        this.cardLevel = cardLevel;
        this.prestigePoints = prestigePoints;
        this.benefit = benefit;

        this.costs = new HashMap<>();
        this.costs.putIfAbsent(new Emerald(), emeraldCost);
        this.costs.putIfAbsent(new Diamond(), diamondCost);
        this.costs.putIfAbsent(new Sapphire(), sapphireCost);
        this.costs.putIfAbsent(new Onyx(), onyxCost);
        this.costs.putIfAbsent(new Ruby(), rubyCost);
    }
}

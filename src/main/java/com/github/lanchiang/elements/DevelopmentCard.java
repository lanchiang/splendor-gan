package com.github.lanchiang.elements;

import java.util.Map;

/**
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class DevelopmentCard {

    private Level cardLevel;

    private Map<Gemstone, Integer> costs;

    /**
     * The prestige points provided by this development card.
     */
    private Integer prestigePoints;

    /**
     * The benefit given by owning this {@link Gemstone}
     */
    private Gemstone benefit;

    public enum Level {
        One, Two, Three
    }
}

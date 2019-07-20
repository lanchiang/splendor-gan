package com.github.lanchiang.elements;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class NobleTile {

    @Getter
    private int prestigePoints = 3;

    @Getter
    private Map<Gemstone, Integer> costs;

    public NobleTile(int emeraldCost, int diamondCost, int sapphireCost, int onyxCost, int rubyCost) {
        costs = new HashMap<>();

        costs.putIfAbsent(new Emerald(), emeraldCost);
        costs.putIfAbsent(new Diamond(), diamondCost);
        costs.putIfAbsent(new Sapphire(), sapphireCost);
        costs.putIfAbsent(new Onyx(), onyxCost);
        costs.putIfAbsent(new Ruby(), rubyCost);
    }
}

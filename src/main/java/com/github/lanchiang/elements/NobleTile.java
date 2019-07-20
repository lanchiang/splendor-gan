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

        costs.putIfAbsent(Gemstone.Emerald, emeraldCost);
        costs.putIfAbsent(Gemstone.Diamond, diamondCost);
        costs.putIfAbsent(Gemstone.Sapphire, sapphireCost);
        costs.putIfAbsent(Gemstone.Onyx, onyxCost);
        costs.putIfAbsent(Gemstone.Ruby, rubyCost);
    }
}

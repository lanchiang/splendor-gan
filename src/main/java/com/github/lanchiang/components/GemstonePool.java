package com.github.lanchiang.components;

import lombok.Getter;

/**
 * This gemstone pool maintains the unoccupied gemstones.
 *
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class GemstonePool {

    @Getter
    private int emeralds;

    @Getter
    private int diamonds;

    @Getter
    private int sapphires;

    @Getter
    private int onyxes;

    @Getter
    private int rubies;

    @Getter
    private int goldJokers;

    private static GemstonePool instance;

    private GemstonePool(int playerNumber) {
        goldJokers = 5; // there are always five gold jokers.
        switch (playerNumber) {
            case 2:
                initPool(4);
                break;
            case 3:
                initPool(5);
                break;
            case 4:
                initPool(7);
                break;
            default:
                throw new IllegalArgumentException("Player number is incorrect.");
        }
    }

    public static GemstonePool getInstance(int playerNumber) {
        if (instance == null) {
            instance = new GemstonePool(playerNumber);
        }
        return instance;
    }

    private void initPool(int numOfBasicGemstone) {
        emeralds = numOfBasicGemstone;
        diamonds = numOfBasicGemstone;
        sapphires = numOfBasicGemstone;
        onyxes = numOfBasicGemstone;
        rubies = numOfBasicGemstone;
    }
}

package com.github.lanchiang.elements;

/**
 * This gemstone pool maintains the unoccupied gemstones.
 *
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class GemstonePool {

    private Emerald[] emeralds;

    private Diamond[] diamonds;

    private Sapphire[] sapphires;

    private Onyx[] onyxes;

    private Ruby[] rubies;

    private GoldJoker[] goldJokers;

    public GemstonePool(int playerNumber) {
        goldJokers = new GoldJoker[5]; // there are always five gold jokers.
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

    private void initPool(int numOfBasicGemstone) {
        emeralds = new Emerald[numOfBasicGemstone];
        diamonds = new Diamond[numOfBasicGemstone];
        sapphires = new Sapphire[numOfBasicGemstone];
        onyxes = new Onyx[numOfBasicGemstone];
        rubies = new Ruby[numOfBasicGemstone];
    }
}

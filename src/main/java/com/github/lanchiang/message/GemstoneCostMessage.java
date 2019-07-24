package com.github.lanchiang.message;

import com.github.lanchiang.components.Gemstone;
import lombok.Getter;

/**
 * @author Lan Jiang
 * @since 2019-07-24
 */
public class GemstoneCostMessage {

    @Getter
    private final int emeralds;

    @Getter
    private final int diamonds;

    @Getter
    private final int sapphires;

    @Getter
    private final int onyxes;

    @Getter
    private final int rubies;

    @Getter
    private final int goldJokers;

    public GemstoneCostMessage(int emeralds, int diamonds, int sapphires, int onyxes, int rubies, int goldJokers) {
        this.emeralds = emeralds;
        this.diamonds = diamonds;
        this.sapphires = sapphires;
        this.onyxes = onyxes;
        this.rubies = rubies;
        this.goldJokers = goldJokers;
    }

    public int getCost(Gemstone gemstone) {
        switch (gemstone) {
            case Onyx: return onyxes;
            case Ruby: return rubies;
            case Diamond: return diamonds;
            case Emerald: return emeralds;
            case Sapphire: return sapphires;
            case GoldJoker: return goldJokers;
            default: throw new IllegalArgumentException();
        }
    }
}

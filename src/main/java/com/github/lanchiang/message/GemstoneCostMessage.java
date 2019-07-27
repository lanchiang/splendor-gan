package com.github.lanchiang.message;

import com.github.lanchiang.components.Gemstone;
import lombok.Getter;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public GemstoneCostMessage(int emeralds, int diamonds, int sapphires, int onyxes, int rubies) {
        this(emeralds, diamonds, sapphires, onyxes, rubies, 0);
    }

    public GemstoneCostMessage(List<Gemstone> gemstones) {
        Map<Gemstone, List<Gemstone>> map = gemstones.stream().collect(Collectors.groupingBy(gemstone -> gemstone));
        emeralds = map.getOrDefault(Gemstone.Emerald, Collections.emptyList()).size();
        diamonds = map.getOrDefault(Gemstone.Diamond, Collections.emptyList()).size();
        sapphires = map.getOrDefault(Gemstone.Sapphire, Collections.emptyList()).size();
        onyxes = map.getOrDefault(Gemstone.Onyx, Collections.emptyList()).size();
        rubies = map.getOrDefault(Gemstone.Ruby, Collections.emptyList()).size();

        goldJokers = 0;
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

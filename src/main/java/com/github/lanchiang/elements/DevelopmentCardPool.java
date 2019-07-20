package com.github.lanchiang.elements;

import com.github.lanchiang.pojo.DevelopmentCardPojo;
import com.github.lanchiang.pojo.DevelopmentCardPoolPojo;
import com.github.lanchiang.pojo.XmlDeserializer;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class DevelopmentCardPool {

    @Getter
    private Collection<DevelopmentCard> pool;

    private Map<Integer, List<DevelopmentCard>> displayedCardsByLevel;

    private Map<Integer, Queue<DevelopmentCard>> unrevealedCardsByLevel;

    private static DevelopmentCardPool instance;

    private DevelopmentCardPool() {
        DevelopmentCardPoolPojo pojo = XmlDeserializer.createPojoObject(getClass().getClassLoader().getResource("development-cards.xml").getPath(), DevelopmentCardPoolPojo.class);
        Collection<DevelopmentCardPojo> developmentCardPools = pojo.getDevelopmentCardPojos();
        pool = developmentCardPools.stream().map(developmentCardPojo ->
                new DevelopmentCard(
                        developmentCardPojo.getLevel(),
                        developmentCardPojo.getPrestige(),
                        Gemstone.getGemstone(developmentCardPojo.getBenefit()),
                        developmentCardPojo.getGemstoneCostPojo().getEmerald(),
                        developmentCardPojo.getGemstoneCostPojo().getDiamond(),
                        developmentCardPojo.getGemstoneCostPojo().getSapphire(),
                        developmentCardPojo.getGemstoneCostPojo().getOnyx(),
                        developmentCardPojo.getGemstoneCostPojo().getRuby())).collect(Collectors.toSet());

        Map<Integer, List<DevelopmentCard>> unrevealedCardListByLevel = pool.stream().collect(Collectors.groupingBy(DevelopmentCard::getCardLevel));
        unrevealedCardsByLevel = new HashMap<>();
        unrevealedCardListByLevel.forEach((key, value) -> unrevealedCardsByLevel.putIfAbsent(key, new LinkedList<>(value)));
        displayedCardsByLevel = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            displayedCardsByLevel.putIfAbsent(i, new ArrayList<>());
        }
    }

    public static DevelopmentCardPool getInstance() {
        if (instance == null) {
            instance = new DevelopmentCardPool();
        }
        return instance;
    }

    /**
     * Displaying the first four cards from each of the levels.
     */
    public void initDisplay() {
        unrevealedCardsByLevel.entrySet().forEach(level -> {
            displayedCardsByLevel.putIfAbsent(level.getKey(), new LinkedList<>());
            for (int i = 0; i < 4; i++) {
                displayedCardsByLevel.get(level.getKey()).add(unrevealedCardsByLevel.get(level.getKey()).poll());
            }
        });
    }
}

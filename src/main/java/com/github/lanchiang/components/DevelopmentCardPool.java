package com.github.lanchiang.components;

import com.github.lanchiang.pojo.DevelopmentCardPojo;
import com.github.lanchiang.pojo.DevelopmentCardPoolPojo;
import com.github.lanchiang.pojo.XmlDeserializer;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The pool of all development cards transitioned in a game. It includes cards on players' hands, card displayed on the board, and cards still undisplayed.
 *
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class DevelopmentCardPool {

    @Getter
    private Collection<DevelopmentCard> pool;

    /**
     * The face-up cards on the board, by their levels.
     */
    private Map<Integer, List<DevelopmentCard>> displayedCardsByLevel;

    /**
     * The face-down cards on the board.
     */
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
                        developmentCardPojo.getGemstoneCostPojo().getRuby()
                )
        ).collect(Collectors.toSet());

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
        unrevealedCardsByLevel.forEach((key, value) -> {
            displayedCardsByLevel.putIfAbsent(key, new LinkedList<>());
            for (int i = 0; i < 4; i++) {
                DevelopmentCard polledCard = unrevealedCardsByLevel.get(key).poll();
                assert polledCard != null;
                polledCard.setState(DevelopmentCard.CardState.Displayed);
                displayedCardsByLevel.get(key).add(polledCard);
            }
        });
    }

    /**
     * After a player acquire a card from the development card pool, display a new one from this level if possible, otherwise do not draw anything.
     * @param level the level where the new card should be displayed.
     */
    public void displayNewCard(int level) {
        DevelopmentCard developmentCard = unrevealedCardsByLevel.get(level).poll();
        if (developmentCard != null) {
            Validate.isTrue(displayedCardsByLevel.get(level).size() == 4); // only at most four cards from each level can be displayed.
            displayedCardsByLevel.get(level).add(developmentCard);
        }
    }
}

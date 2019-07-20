package com.github.lanchiang.elements;

import com.github.lanchiang.pojo.DevelopmentCardPojo;
import com.github.lanchiang.pojo.DevelopmentCardPoolPojo;
import com.github.lanchiang.pojo.XmlDeserializer;
import lombok.Getter;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class DevelopmentCardPool {

    @Getter
    private Collection<DevelopmentCard> pool;

    private static DevelopmentCardPool instance;

    private DevelopmentCardPool() {
        DevelopmentCardPoolPojo pojo = XmlDeserializer.createPojoObject(getClass().getClassLoader().getResource("development-cards.xml").getPath(), DevelopmentCardPoolPojo.class);
        Collection<DevelopmentCardPojo> developmentCardPools = pojo.getDevelopmentCardPojos();
        pool = developmentCardPools.stream().map(developmentCardPojo ->
                new DevelopmentCard(
                        developmentCardPojo.getLevel(),
                        developmentCardPojo.getPrestige(),
                        Gemstone.createGemstoneInstance(developmentCardPojo.getBenefit()),
                        developmentCardPojo.getGemstoneCostPojo().getEmerald(),
                        developmentCardPojo.getGemstoneCostPojo().getDiamond(),
                        developmentCardPojo.getGemstoneCostPojo().getSapphire(),
                        developmentCardPojo.getGemstoneCostPojo().getOnyx(),
                        developmentCardPojo.getGemstoneCostPojo().getRuby())).collect(Collectors.toSet());
    }

    public static DevelopmentCardPool getInstance() {
        if (instance == null) {
            instance = new DevelopmentCardPool();
        }
        return instance;
    }
}

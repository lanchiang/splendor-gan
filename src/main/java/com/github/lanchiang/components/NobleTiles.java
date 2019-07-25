package com.github.lanchiang.components;

import com.github.lanchiang.pojo.NobleTilePoolPojo;
import com.github.lanchiang.pojo.XmlDeserializer;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The collection of noble tiles.
 *
 * @author Lan Jiang
 * @since 2019-07-20
 */
public class NobleTiles {

    @Getter
    private Collection<NobleTile> nobleTiles;

    private static NobleTiles instance;

    private NobleTiles() {
        NobleTilePoolPojo pojo = XmlDeserializer.createPojoObject(getClass().getClassLoader().getResource("noble-tiles.xml").getPath(), NobleTilePoolPojo.class);
        nobleTiles = pojo.getNobleTilePojos().stream().map(nobleTilePojo -> new NobleTile(
                nobleTilePojo.getGemstoneCostPojo().getEmerald(),
                nobleTilePojo.getGemstoneCostPojo().getDiamond(),
                nobleTilePojo.getGemstoneCostPojo().getSapphire(),
                nobleTilePojo.getGemstoneCostPojo().getOnyx(),
                nobleTilePojo.getGemstoneCostPojo().getRuby()
        )).collect(Collectors.toSet());
    }

    public static NobleTiles getInstance() {
        if (instance == null) {
            instance = new NobleTiles();
        }
        return instance;
    }

    public Collection<NobleTile> takeRandom(int numOfNobleTiles) {
        List<NobleTile> nobleTileList = new LinkedList<>(nobleTiles);
        Collections.shuffle(nobleTileList);
        return new HashSet<>(nobleTileList.subList(0, numOfNobleTiles));
    }
}

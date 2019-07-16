package com.github.lanchiang.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class NobleTilePojo {

    @JsonProperty("gemstone-card-cost")
    private GemstoneCostPojo gemstoneCostPojo;

    @JsonProperty("prestige")
    private int prestige;
}

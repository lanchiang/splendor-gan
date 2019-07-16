package com.github.lanchiang.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class DevelopmentCardPojo {

    @JsonProperty("level")
    private int level;

    @JsonProperty("prestige")
    private int prestige;

    @JsonProperty("benefit")
    private String benefit;

    @JsonProperty("gemstone-cost")
    protected GemstoneCostPojo gemstoneCostPojo;
}

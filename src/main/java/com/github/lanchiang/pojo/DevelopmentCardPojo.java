package com.github.lanchiang.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class DevelopmentCardPojo {

    @JsonProperty("level")
    @Getter
    private int level;

    @JsonProperty("prestige")
    @Getter
    private int prestige;

    @JsonProperty("benefit")
    @Getter
    private String benefit;

    @JsonProperty("gemstone-cost")
    @Getter
    protected GemstoneCostPojo gemstoneCostPojo;
}

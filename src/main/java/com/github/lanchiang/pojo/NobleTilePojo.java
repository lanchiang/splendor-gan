package com.github.lanchiang.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class NobleTilePojo {

    @JsonProperty("gemstone-card-cost")
    @Getter
    private GemstoneCostPojo gemstoneCostPojo;

    @JsonProperty("prestige")
    @Getter
    private int prestige;
}

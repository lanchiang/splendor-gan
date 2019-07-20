package com.github.lanchiang.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class GemstoneCostPojo {

    @JsonProperty("emerald")
    @Getter
    private int emerald;

    @JsonProperty("diamond")
    @Getter
    private int diamond;

    @JsonProperty("sapphire")
    @Getter
    private int sapphire;

    @JsonProperty("onyx")
    @Getter
    private int onyx;

    @JsonProperty("ruby")
    @Getter
    private int ruby;
}

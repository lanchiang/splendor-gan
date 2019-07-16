package com.github.lanchiang.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class GemstoneCostPojo {

    @JsonProperty("emerald")
    private int emerald;

    @JsonProperty("diamond")
    private int diamond;

    @JsonProperty("sapphire")
    private int sapphire;

    @JsonProperty("onyx")
    private int onyx;

    @JsonProperty("ruby")
    private int ruby;
}

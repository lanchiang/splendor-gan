package com.github.lanchiang.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;

import java.util.Collection;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class DevelopmentCardPoolPojo {

    @JsonProperty("development-card")
    @JacksonXmlElementWrapper(useWrapping = false)
    @Getter
    private Collection<DevelopmentCardPojo> developmentCardPojos;
}

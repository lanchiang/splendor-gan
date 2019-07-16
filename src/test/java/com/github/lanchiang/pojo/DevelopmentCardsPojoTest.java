package com.github.lanchiang.pojo;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class DevelopmentCardsPojoTest {

    @Test
    public void testLoadXml() throws URISyntaxException {
        XmlDeserializer deserializer = new XmlDeserializer();
        DevelopmentCardPoolPojo xml = deserializer.createPojoObject(getClass().getClassLoader().getResource("development-cards.xml").getPath(), DevelopmentCardPoolPojo.class);
        return;
    }
}

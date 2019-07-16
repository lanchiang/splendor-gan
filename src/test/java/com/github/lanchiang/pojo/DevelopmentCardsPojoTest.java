package com.github.lanchiang.pojo;

import org.junit.Test;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class DevelopmentCardsPojoTest {

    @Test
    public void testLoadXml() {
        DevelopmentCardPoolPojo xml = XmlDeserializer.createPojoObject(getClass().getClassLoader().getResource("development-cards.xml").getPath(), DevelopmentCardPoolPojo.class);
        return;
    }
}

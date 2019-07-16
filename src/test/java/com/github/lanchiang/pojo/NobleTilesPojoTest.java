package com.github.lanchiang.pojo;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class NobleTilesPojoTest {

    @Test
    public void testLoadXml() {
        NobleTilePoolPojo pojo = XmlDeserializer.createPojoObject(getClass().getClassLoader().getResource("noble-tiles.xml").getPath(), NobleTilePoolPojo.class);
        Assert.assertNotEquals(pojo, null);
    }
}

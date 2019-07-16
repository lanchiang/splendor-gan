package com.github.lanchiang.pojo;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

/**
 * @author Lan Jiang
 * @since 2019-07-16
 */
public class XmlDeserializer {

    private XmlMapper xmlMapper = new XmlMapper();

    public <T> T createPojoObject(String fileName, final Class<T> xmlClass) {
        T pojo = null;
        try {
            pojo = xmlMapper.readValue(new File(fileName), xmlClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pojo;
    }
}

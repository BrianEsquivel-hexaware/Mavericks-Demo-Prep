package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    private static Properties _props = new Properties();

    static {
        try(InputStream input = PropertyUtils.class.getClassLoader().getResourceAsStream("app.properties")) {
            if(input == null) {
                throw new RuntimeException("app.properties not found");
            }
            _props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading app.properties", e);
        }
    }

    public static String getProperty(String key) {
        return _props.getProperty(key);
    }
}

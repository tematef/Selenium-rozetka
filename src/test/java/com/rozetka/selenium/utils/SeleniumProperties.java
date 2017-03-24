package com.rozetka.selenium.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by artem on 3/24/17.
 */
public class SeleniumProperties {

    private static Properties properties;

    private SeleniumProperties() {
    }

    static {
        properties = new Properties();
        URL props = ClassLoader.getSystemResource("selenium.properties");
        try {
            properties.load(props.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        }
        return properties.getProperty(key);
    }
}

package utils;

import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {
    private final static Properties properties = initProperties();

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    private static Properties initProperties() {
        Properties properties;
        String sourceFile = "testData.properties";
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(sourceFile);
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Can not read properties file: " + sourceFile);
        }
        return properties;
    }
}

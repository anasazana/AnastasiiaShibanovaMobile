package utils;

import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {
    private static volatile Properties properties;

    private static void initProperties() {
        String sourceFile = "testData.properties";
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(sourceFile);
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Can not read properties file: " + sourceFile);
        }
    }

    private static Properties getProperties() {
        if (properties == null) {
            synchronized (PropertyReader.class) {
                if (properties == null) {
                    initProperties();
                }
            }
        }
        return properties;
    }

    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }
}

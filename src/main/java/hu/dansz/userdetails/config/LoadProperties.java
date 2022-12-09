package hu.dansz.userdetails.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    public Properties getProperties() {
        Properties properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/main/java/hu/dansz/userdetails/WicketApplication.properties");
            properties.load(ip);
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

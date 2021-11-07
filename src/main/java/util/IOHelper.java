package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IOHelper {


    // reads and returns Discord API authentication token from properties file
    public static String readToken() {
        String propFile = "src/main/resources/token.properties";

        try (InputStream input = new FileInputStream(propFile)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("token");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IOHelper {

    private static final Logger logger = LoggerFactory.getLogger("IOHelper.class");


    // reads and returns Discord API authentication token from properties file
    public static String readToken() {
        String propFile = "src/main/resources/credentials.properties";

        try (InputStream input = new FileInputStream(propFile)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("token");
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
            return null;
        }
    }

    public static long readGuildId() {
        String propFile = "src/main/resources/credentials.properties";

        try (InputStream input = new FileInputStream(propFile)) {
            Properties prop = new Properties();
            prop.load(input);
            return Long.parseLong(prop.getProperty("guild_id"));
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
            return 0;
        }
    }
}

package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IOHelper {

    private static final Logger logger = LoggerFactory.getLogger("IOHelper.class");
    private static final String propFile = "src/main/resources/token.properties";

    /** Returns Discord API authentication token from properties file
     *
     * @return Token
     */
    public static String readDiscordApiToken() {
        try {
            InputStream input = new FileInputStream(propFile);
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("discord_api_token");
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
            return null;
        }
    }

    /**
     * Returns personal Discord Guild ID
     *
     * @return guild ID value
     */
    public static long readGuildId() {
        try {
            InputStream input = new FileInputStream(propFile);
            Properties prop = new Properties();
            prop.load(input);
            return Long.parseLong(prop.getProperty("guild_id"));
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
            return 0;
        }
    }

    /**
     * Returns Openweathermap.org API key
     * @see {https://openweathermap.org/api}
     * @return OWM API key
     */
    public static String readOwmApiToken() {
        try {
            InputStream input = new FileInputStream(propFile);
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("owm_api_token");
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
            return null;
        }
    }
}

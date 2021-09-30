package utils.common.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

    private static Logger logger = LogManager.getLogger(Log.class.getName());

    public static void loggerProperty() {
        PropertyConfigurator.configure("log4j.properties");
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void log(String message){
        logger.info(message);
    }
}
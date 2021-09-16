package utils;

import org.apache.logging.log4j.LogManager;

import java.io.File;

public class Logger {

    public Logger() {}

    public static org.apache.logging.log4j.Logger log = LogManager.getLogger(Logger.class);

    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message, Throwable throwable) {
        log.error(message, throwable);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void trace(String message) {
        log.trace(message);
    }

    public static void log(String message){
        log.info(message);
    }

    public static void attach(File filePath, String message) {
        log.info("RP_MESSAGE#FILE#{}#{}", filePath, message);
    }
}

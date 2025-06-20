package org.mocion.app.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;

public class AppiumServerManager {
    private static AppiumDriverLocalService service;
    private static final Logger logger = LogManager.getLogger(AppiumServerManager.class);

    private AppiumServerManager() {
    }

    public static void startServer(int port) {
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdir(); // Create directory if it doesn't exist
        }

        service = new AppiumServiceBuilder()
                .usingPort(port)  // Assign a specific port
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                .withLogFile(new File("logs/appium_" + port + ".log"))
                .withIPAddress("127.0.0.1")
                .build();
        service.start();
        logger.info("Appium Server started on: {}", getServerUrl());
    }


    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            logger.error("Appium Server stopped.");
        } else {
            logger.warn("Appium Server is not running.");
        }

        // Cleanup or delete the log file if needed (ensure no other process is locking it)
        File logFile = new File("logs/appium_4723.log");
        if (logFile.exists()) {
            boolean deleted = logFile.delete();
            if (deleted) {
                logger.info("Log file deleted: {}", logFile.getPath());
            } else {
                logger.warn("Failed to delete log file: {}", logFile.getPath());
            }
        }
    }

    public static URL getServerUrl() {
        if (service != null) {
            return service.getUrl();
        } else {
            logger.error("Appium service is not initialized.");
            return null;
        }
    }
}

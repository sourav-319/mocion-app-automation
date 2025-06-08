package org.mocion.app.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import org.mocion.app.exceptions.CustomRuntimeException;
import org.mocion.app.exceptions.FileNotFoundException;

import java.io.File;
import java.io.FileReader;

import static org.mocion.app.drivers.DriverFactory.getDriver;

/**
 * LocatorManager is a singleton class responsible for managing locators used in the application.
 * It loads locators from a JSON file based on the platform name and provides a single instance of itself.
 */

public class LocatorManager {
    private static LocatorManager instance = null;

    // Retrieve the locators
    @Getter
    public static final JsonObject locators;

    /**
     * Private constructor to prevent instantiation.
     * It calls the loadLocators method to load locators from the JSON file.
     */
    private LocatorManager() {
    }

    /**
     * Provides the single instance of LocatorManager.
     * If the instance is null, it creates a new instance.
     *
     * @return the single instance of LocatorManager
     */
    public static synchronized LocatorManager getInstance() {
        if (instance == null) {
            instance = new LocatorManager();
        }
        return instance;
    }

    static {
        try {
            String platform = getDriver().getCapabilities().getCapability("platformName").toString().toLowerCase();
            String filePath = String.format("src/test/resources/locators/%s_locators.json", platform);

            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("Locator file not found: " + filePath);
            }

            locators = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
        } catch (Exception e) {
            throw new CustomRuntimeException("Failed to load locators: " + e.getMessage(), e);
        }
    }
}

package org.mocion.app.pages;

import com.google.gson.JsonObject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.mocion.app.constants.FrameworkConstant;
import org.mocion.app.utils.LocatorManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class BasePage {
    protected AppiumDriver driver;
    private static final JsonObject locators;

    static {
        // Load locators from the singleton
        locators = LocatorManager.getLocators();
    }

    /**
     * Constructs a new BasePage instance.
     *
     * @param driver The AppiumDriver instance to use for interacting with the app.
     * @throws RuntimeException If the locator file cannot be found or loaded.
     */
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public final void click(String screen, String element) {
        WebElement webElement = findElement(screen, element);
        webElement.click();
    }

    public final void type(String screen, String element, String value) {
        WebElement webElement = findElement(screen, element);
        webElement.sendKeys(value);
    }

    /**
     * Retrieves the locator for a given screen and element.
     *
     * @param screen  The screen name where the element is located.
     * @param element The element name to find.
     * @return The locator for the specified element.
     * @throws IllegalArgumentException If the screen or element is not found, or if the locator format is invalid.
     */
    protected By getLocator(String screen, String element) {
        if (!locators.has(screen)) {
            throw new IllegalArgumentException("Screen '" + screen + "' not found in locators file.");
        }

        JsonObject screenLocators = locators.getAsJsonObject(screen);
        if (!screenLocators.has(element)) {
            throw new IllegalArgumentException("Element '" + element + "' not found in screen '" + screen + "'.");
        }

        String locator = screenLocators.get(element).getAsString();

        // Replace {{TODAY}} with current date
        if (locator.contains("{{TODAY}}")) {
            String today = new java.text.SimpleDateFormat("EEEE, MMMM d, yyyy").format(new java.util.Date());
            locator = locator.replace("{{TODAY}}", today);
        }

        String[] parts = locator.split(":", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid locator format: " + locator);
        }

        String strategy = parts[0].trim();
        String value = parts[1].trim();

        return switch (strategy.toLowerCase()) {
            case "ui" -> AppiumBy.androidUIAutomator(value);
            case "id" -> AppiumBy.id(value);
            case "xpath" -> By.xpath(value);
            case "accessibility_id" -> new AppiumBy.ByAccessibilityId(value);
            case "name" -> AppiumBy.name(value);
            default -> throw new IllegalArgumentException("Invalid locator strategy: " + strategy);
        };
    }

    /**
     * Waits for a web element to be visible on the screen.
     *
     * @param locator The locator of the web element to wait for.
     * @return The located WebElement once it is visible.
     */
    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstant.WAIT_STRATEGY));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Finds a web element on the screen by its locator.
     *
     * @param screen  The screen name where the element is located.
     * @param element The element name to find.
     * @return The located WebElement.
     */
    protected WebElement findElement(String screen, String element) {
        if (element.equals("add_menu")) {
            System.out.println(screen + " " + element);
        }
        return waitForElement(getLocator(screen, element));
    }

    public void clear(String screen, String locator) {
        findElement(screen, locator).clear();
    }

    public String getText(String screen, String locator) {
        return findElement(screen, locator).getText();
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    public void scrollUntilVisible(String screen, String element) {
        int maxScrolls = 5;
        int attempt = 0;

        while (attempt < maxScrolls) {
            try {
                WebElement targetElement = driver.findElement(getLocator(screen, element));
                if (targetElement.isDisplayed()) {
                    break;
                }
            } catch (Exception ignored) {
                // Not visible, keep scrolling
            }

            int screenHeight = driver.manage().window().getSize().height;
            int screenWidth = driver.manage().window().getSize().width;

            int startX = screenWidth / 2;
            int startY = (int) (screenHeight * 0.8); // bottom
            int endY = (int) (screenHeight * 0.2);   // top

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence scroll = new Sequence(finger, 1);

            scroll.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), startX, startY));
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            scroll.addAction(finger.createPointerMove(Duration.ofMillis(800),
                    PointerInput.Origin.viewport(), startX, endY));
            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(scroll));

            attempt++;
        }
    }
}

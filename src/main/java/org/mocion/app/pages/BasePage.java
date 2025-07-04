package org.mocion.app.pages;

import com.google.gson.JsonObject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.mocion.app.constants.FrameworkConstant;
import org.mocion.app.utils.LocatorManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected AppiumDriver driver;
    private static final JsonObject locators;

    static {
        // Load locators from the singleton
        locators = LocatorManager.getLocators();
    }

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

    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstant.WAIT_STRATEGY));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

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

    public boolean scrollUntilVisible(String screen, String element) {
        int maxScrolls = 5;
        int attempt = 0;

        while (attempt < maxScrolls) {
            try {
                WebElement targetElement = driver.findElement(getLocator(screen, element));
                if (targetElement.isDisplayed()) {
                    int screenHeight = driver.manage().window().getSize().height;
                    int screenWidth = driver.manage().window().getSize().width;

                    int startX = screenWidth / 2;
                    int startY = (int) (screenHeight * 0.6);
                    int endY = (int) (screenHeight * 0.4);

                    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
                    Sequence shortScroll = new Sequence(finger, 1);

                    shortScroll.addAction(finger.createPointerMove(Duration.ofMillis(0),
                            PointerInput.Origin.viewport(), startX, startY));
                    shortScroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    shortScroll.addAction(finger.createPointerMove(Duration.ofMillis(300),
                            PointerInput.Origin.viewport(), startX, endY));
                    shortScroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                    driver.perform(List.of(shortScroll));
                    return true;
                }
            } catch (NoSuchElementException ignored) {
            }

            int screenHeight = driver.manage().window().getSize().height;
            int screenWidth = driver.manage().window().getSize().width;

            int startX = screenWidth / 2;
            int startY = (int) (screenHeight * 0.8);
            int endY = (int) (screenHeight * 0.2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence scroll = new Sequence(finger, 1);

            scroll.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), startX, startY));
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            scroll.addAction(finger.createPointerMove(Duration.ofMillis(800),
                    PointerInput.Origin.viewport(), startX, endY));
            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(List.of(scroll));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            attempt++;
        }

        return false;
    }
}

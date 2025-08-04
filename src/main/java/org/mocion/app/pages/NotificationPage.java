package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NotificationPage extends BasePage {
    private static final String NOTIFICATION_SCREEN = "notification_screen";

    public NotificationPage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement addPlayerToEventNotificationLocator() {
        By locator = getLocator(NOTIFICATION_SCREEN, "add_player_to_event_success_notification");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

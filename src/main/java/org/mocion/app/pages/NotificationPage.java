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
        By locator = getLocator(NOTIFICATION_SCREEN, "add_player_to_event_notification");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement eventWaitingListNotificationLocator() {
        By locator = getLocator(NOTIFICATION_SCREEN, "event_waiting_list_notification");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement academyPaymentReminderNotificationLocator() {
        By locator = getLocator(NOTIFICATION_SCREEN, "academy_payment_reminder_notification");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement gameWithCoachRefundNotificationLocator() {
        By locator = getLocator(NOTIFICATION_SCREEN, "game_with_coach_refund_notification");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

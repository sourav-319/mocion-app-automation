package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AcademyDetailsPage extends BasePage {
    private static final String ACADEMY_DETAILS_SCREEN = "academy_details_screen";

    public AcademyDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public AcademyDetailsPage clickSubscribeByPaymentButton() {
        scrollUntilVisible(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
        click(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
        return this;
    }

    public void clickCancelAcademySubscriptionButton() {
        scrollUntilVisible(ACADEMY_DETAILS_SCREEN, "cancel_academy_subscription_button");
        click(ACADEMY_DETAILS_SCREEN, "cancel_academy_subscription_button");
    }

    public void clickConfirmPaymentInfoButton() {
        click(ACADEMY_DETAILS_SCREEN, "confirm_payment_info_button");
    }

    public String getSubscribeByPaymentButtonText() {
        scrollUntilVisible(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
        return getText(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
    }

    public String getAcademyPriceTextFromDetails() {
        return getText(ACADEMY_DETAILS_SCREEN, "academy_price_text");
    }

    public WebElement subscribeByPaymentButtonLocator() {
        scrollUntilVisible(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
        By locator = getLocator(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement playersListLocator() {
        By locator = getLocator(ACADEMY_DETAILS_SCREEN, "players_list_locator");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

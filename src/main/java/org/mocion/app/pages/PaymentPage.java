package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage extends BasePage {
    private static final String PAYMENT_SCREEN = "payment_screen";

    public PaymentPage(AppiumDriver driver) {
        super(driver);
    }

    public PaymentPage fillCardNumber() {
        click(PaymentPage.PAYMENT_SCREEN, "card_number_field");
        type(PAYMENT_SCREEN, "card_number_field", "4111 1111 1111 1111");
        return this;
    }

    public PaymentPage fillCVVNumber() {
        click(PAYMENT_SCREEN, "cvv_number_field");
        type(PAYMENT_SCREEN, "cvv_number_field", "123");
        return this;
    }

    public PaymentPage clickMakePaymentButton() {
        click(PAYMENT_SCREEN, "make_payment_button");
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        return this;
    }

    public void clickAcceptPaymentButton() {
        click(PaymentPage.PAYMENT_SCREEN, "accept_payment_button");
    }

    public WebElement waitForPaymentSuccessElement() {
        By locator = getLocator(PAYMENT_SCREEN, "payment_successful_locator");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
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

    public PaymentPage fillCardNumber(String cardNumber) {
        type(PAYMENT_SCREEN, "card_number_field", cardNumber);
        return this;
    }

    public PaymentPage fillCVVNumber(String cvvNumber) {
        type(PAYMENT_SCREEN, "cvv_number_field", cvvNumber);
        return this;
    }

    public PaymentPage clickMakePaymentButton() {
        click(PAYMENT_SCREEN, "make_payment_button");
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

    public String getAcademyPriceText() {
        return getText(PAYMENT_SCREEN, "academy_price_text");
    }
}

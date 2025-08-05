package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingDetailsPage extends BasePage {
    private static final String BOOKING_DETAILS_SCREEN = "booking_details_screen";

    public BookingDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public BookingDetailsPage clickCancelBookingButton() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "cancel_booking_button");
        click(BOOKING_DETAILS_SCREEN, "cancel_booking_button");
        return this;
    }

    public BookingDetailsPage selectSplitPayment() {
        click(BOOKING_DETAILS_SCREEN, "split_payment");
        return this;
    }

    public BookingDetailsPage selectFullPayment() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "full_payment");
        click(BOOKING_DETAILS_SCREEN, "full_payment");
        return this;
    }

    public void clickConfirmTotalPaymentButton() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "confirm_total_payment_button");
        click(BOOKING_DETAILS_SCREEN, "confirm_total_payment_button");
    }

    public void clickConfirmPaymentButton() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "confirm_payment_button");
        click(BOOKING_DETAILS_SCREEN, "confirm_payment_button");
    }

    public void clickYesToCancelBooking() {
        click(BOOKING_DETAILS_SCREEN, "yes_to_cancel_booking_button");
    }

    public WebElement confirmBookingSuccessMessageLocator() {
        By locator = getLocator(BOOKING_DETAILS_SCREEN, "confirm_booking_success_message");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

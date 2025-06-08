package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class BookingDetailsPage extends BasePage {
    private static final String BOOKING_DETAILS_SCREEN = "booking_details_screen";

    public BookingDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickConfirmPaymentButton() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "confirm_payment_button");
        click(BOOKING_DETAILS_SCREEN, "confirm_payment_button");
    }
}

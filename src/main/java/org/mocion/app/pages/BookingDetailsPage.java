package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

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

    public void clickBackIcon() {
        click(BOOKING_DETAILS_SCREEN, "back_icon");
    }

    public BookingDetailsPage clickConfirmTotalPaymentButton() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "confirm_total_payment_button");
        click(BOOKING_DETAILS_SCREEN, "confirm_total_payment_button");
        return this;
    }

    public void clickOkButton() {
        click(BOOKING_DETAILS_SCREEN, "ok_button");
    }

    public void clickConfirmPaymentButton() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "confirm_payment_button");
        click(BOOKING_DETAILS_SCREEN, "confirm_payment_button");
    }

    public void clickYesToCancelBooking() {
        click(BOOKING_DETAILS_SCREEN, "yes_to_cancel_booking_button");
    }
}

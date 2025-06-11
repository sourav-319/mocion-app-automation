package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class BookingPage extends BasePage {
    private static final String BOOKING_SCREEN = "booking_screen";

    public BookingPage(AppiumDriver driver) {
        super(driver);
    }

    public BookingPage clickBookNowButton() {
        click(BOOKING_SCREEN, "book_now_button");
        return this;
    }

    public BookingPage selectBookingDate() {
        scrollUntilVisible(BOOKING_SCREEN, "select_date");
        click(BOOKING_SCREEN, "select_date");
        return this;
    }

    public BookingPage selectBookingTime() {
        scrollUntilVisible(BOOKING_SCREEN, "select_time");
        click(BOOKING_SCREEN, "select_time");
        return this;
    }

    public BookingPage clickCourtDropdown() {
        scrollUntilVisible(BOOKING_SCREEN, "court_dropdown");
        click(BOOKING_SCREEN, "court_dropdown");
        return this;
    }

    public void selectPrice() {
        scrollUntilVisible(BOOKING_SCREEN, "select_price");
        click(BOOKING_SCREEN, "select_price");
    }
}

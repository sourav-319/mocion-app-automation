package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class MyBookingsPage extends BasePage {
    private static final String MY_BOOKINGS_SCREEN = "my_bookings_screen";

    public MyBookingsPage(AppiumDriver driver) {
        super(driver);
    }

    public void selectBooking() {
        click(MY_BOOKINGS_SCREEN, "select_booking");
    }
}

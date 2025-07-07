package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

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
        scrollUntilVisible(BOOKING_SCREEN, "select_booking_date");
        click(BOOKING_SCREEN, "select_booking_date");
        return this;
    }

    public BookingPage selectBookingTime() {
        scrollUntilVisible(BOOKING_SCREEN, "select_booking_time");
        click(BOOKING_SCREEN, "select_booking_time");
        return this;
    }

    public BookingPage clickCourtDropdown() {
        scrollUntilVisible(BOOKING_SCREEN, "court_dropdown");
        click(BOOKING_SCREEN, "court_dropdown");
        return this;
    }

    public BookingPage selectPrice() {
        click(BOOKING_SCREEN, "select_price");
        return this;
    }

    public void clickContinueButton() {
        click(BOOKING_SCREEN, "continue_button");
    }

    public BookingPage fillSearchKeyword(String searchKeyword) {
        click(BOOKING_SCREEN, "search_input_field");
        type(BOOKING_SCREEN, "search_input_field", searchKeyword);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        return this;
    }

    public BookingPage selectBookingTypePublic() {
        click(BOOKING_SCREEN, "booking_type_dropdown");
        click(BOOKING_SCREEN, "booking_type_public_match");
        return this;
    }

    public BookingPage selectBookingTypePrivate() {
        click(BOOKING_SCREEN, "booking_type_dropdown");
        click(BOOKING_SCREEN, "booking_type_private_match");
        return this;
    }

    public BookingPage selectGameTypeFriendly() {
        click(BOOKING_SCREEN, "game_type_dropdown");
        click(BOOKING_SCREEN, "game_type_friendly");
        return this;
    }

    public BookingPage selectGameTypeCompetitive() {
        click(BOOKING_SCREEN, "game_type_dropdown");
        click(BOOKING_SCREEN, "game_type_competitive");
        return this;
    }
}

package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage {
    private static final String HOME_SCREEN = "home_screen";

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void selectBookMatch() {
        click(HOME_SCREEN, "select_book_match");
    }

    public void selectCompetitive() {
        click(HOME_SCREEN, "select_competitive");
    }

    public void selectFriendly() {
        click(HOME_SCREEN, "select_friendly");
    }

    public void clickMyUpcomingBookings() {
        click(HOME_SCREEN, "my_upcoming_bookings");
    }

    public void clickNotificationIcon() {
        click(HOME_SCREEN, "notification_icon");
    }

    public void selectCoaching() {
        click(HOME_SCREEN, "select_coaching");
    }
}

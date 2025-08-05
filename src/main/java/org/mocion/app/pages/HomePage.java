package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage {
    private static final String HOME_SCREEN = "home_screen";

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public HomePage clickProfileIcon() throws InterruptedException {
        Thread.sleep(2000);
        click(HOME_SCREEN, "profile_icon");
        return this;
    }

    public HomePage clickLogout() {
        click(HOME_SCREEN, "logout_text");
        return this;
    }

    public HomePage clickBack() {
        driver.navigate().back();
        return this;
    }

    public void clickYesToConfirmLogout() {
        click(HOME_SCREEN, "yes_to_confirm_logout");
    }

    public void selectMatch() {
        click(HOME_SCREEN, "select_match");
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
}

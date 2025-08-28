package com.mocion.test;

import org.mocion.app.pages.*;
import org.mocion.app.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CoachingTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public NotificationPage notificationPage;
    public MyBookingsPage myBookingsPage;
    public BookingDetailsPage bookingDetailsPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        notificationPage = new NotificationPage(driver);
        myBookingsPage = new MyBookingsPage(driver);
        bookingDetailsPage = new BookingDetailsPage(driver);
    }

    @Test(description = "Player gets refund notification cancelling booking when payment method is cash or card should successful")
    public void verify_player_gets_refund_notification_cancelling_booking_when_payment_method_is_cash_or_card_should_succeed() {
        initPages();
        userLogin();
        homePage
                .clickMyUpcomingBookings();
        myBookingsPage
                .selectBooking();
        bookingDetailsPage
                .clickCancelBookingButton()
                .clickYesToCancelBooking();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.gameWithCoachRefundNotificationLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Player gets refund notification cancelling booking by admin when payment method is cash or card should successful")
    public void verify_player_gets_refund_notification_cancelling_booking_by_admin_when_payment_method_is_cash_or_card_should_succeed() {
        initPages();
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.gameWithCoachRefundNotificationLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    private void userLogin() {
        initPages();
        loginPage
                .fillUserEmail(ConfigReader.get("user.email"))
                .fillUserPassword(ConfigReader.get("user.password"))
                .clickNextButton();
    }
}

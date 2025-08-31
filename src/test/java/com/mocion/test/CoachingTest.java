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
    public CoachingPage coachingPage;
    public CoachDetailsPage coachingDetailsPage;
    public PackageDetailsPage packageDetailsPage;
    public PaymentPage paymentPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        notificationPage = new NotificationPage(driver);
        myBookingsPage = new MyBookingsPage(driver);
        bookingDetailsPage = new BookingDetailsPage(driver);
        coachingPage = new CoachingPage(driver);
        coachingDetailsPage = new CoachDetailsPage(driver);
        packageDetailsPage = new PackageDetailsPage(driver);
        paymentPage = new PaymentPage(driver);
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

    @Test(description = "Buying private indoor coaching package should successful")
    public void verify_buying_private_indoor_coaching_package_should_succeed() {
        String searchKeyword = "new ch";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .selectCoaches()
                .fillSearchKeyword(searchKeyword)
                .clickBuyAPackageButton();
        coachingDetailsPage
                .selectBookingTypePrivateCoachingPackage()
                .selectCourtTypeIndoor()
                .clickBuyNowButton();
        packageDetailsPage
                .clickBuyNowButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Buying private outdoor coaching package should successful")
    public void verify_buying_private_outdoor_coaching_package_should_succeed() {
        String searchKeyword = "new ch";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .selectCoaches()
                .fillSearchKeyword(searchKeyword)
                .clickBuyAPackageButton();
        coachingDetailsPage
                .selectBookingTypePrivateCoachingPackage()
                .selectCourtTypeOutdoor()
                .clickBuyNowButton();
        packageDetailsPage
                .clickBuyNowButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
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

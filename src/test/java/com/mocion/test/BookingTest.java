package com.mocion.test;

import org.mocion.app.pages.*;
import org.mocion.app.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public BookingPage bookingPage;
    public BookingDetailsPage bookingDetailsPage;
    public PaymentPage paymentPage;
    public MyBookingsPage myBookingsPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        bookingPage = new BookingPage(driver);
        bookingDetailsPage = new BookingDetailsPage(driver);
        paymentPage = new PaymentPage(driver);
        myBookingsPage = new MyBookingsPage(driver);
    }

    @Test(description = "Friendly public Match booking create should successful")
    public void verify_friendly_public_match_booking_create_should_succeed() {
        String searchKeyword = "Farah123";

        initPages();
        userLogin();
        homePage
                .selectMatch();
        bookingPage
                .fillSearchKeyword(searchKeyword)
                .clickBookNowButton()
                .selectBookingTypePublic()
                .selectGameTypeFriendly()
                .selectBookingDate()
                .selectBookingTime()
                .clickCourtDropdown()
                .selectPrice();
        bookingDetailsPage
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Competitive public Match booking create should successful")
    public void verify_competitive_public_match_booking_create_should_succeed() {
        String searchKeyword = "Farah123";

        initPages();
        userLogin();
        homePage
                .selectMatch();
        bookingPage
                .fillSearchKeyword(searchKeyword)
                .clickBookNowButton()
                .selectBookingTypePublic()
                .selectGameTypeCompetitive()
                .selectBookingDate()
                .selectBookingTime()
                .clickCourtDropdown()
                .selectPrice();
        bookingDetailsPage
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Friendly private Match booking create should successful")
    public void verify_friendly_private_match_booking_create_should_succeed() {
        String searchKeyword = "Farah123";

        initPages();
        userLogin();
        homePage
                .selectMatch();
        bookingPage
                .fillSearchKeyword(searchKeyword)
                .clickBookNowButton()
                .selectBookingTypePrivate()
                .selectGameTypeFriendly()
                .selectBookingDate()
                .selectBookingTime()
                .clickCourtDropdown()
                .selectPrice()
                .clickContinueButton();
        bookingDetailsPage
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Competitive private Match booking create should successful")
    public void verify_competitive_private_match_booking_create_should_succeed() {
        String searchKeyword = "Farah123";

        initPages();
        userLogin();
        homePage
                .selectMatch();
        bookingPage
                .fillSearchKeyword(searchKeyword)
                .clickBookNowButton()
                .selectBookingTypePrivate()
                .selectGameTypeCompetitive()
                .selectBookingDate()
                .selectBookingTime()
                .clickCourtDropdown()
                .selectPrice()
                .clickContinueButton();
        bookingDetailsPage
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Friendly private Match booking create with split payment should successful")
    public void verify_friendly_private_match_booking_create_with_spilt_payment_should_succeed() {
        String searchKeyword = "Farah123";

        initPages();
        userLogin();
        homePage
                .selectMatch();
        bookingPage
                .fillSearchKeyword(searchKeyword)
                .clickBookNowButton()
                .selectBookingTypePrivate()
                .selectGameTypeFriendly()
                .selectBookingDate()
                .selectBookingTime()
                .clickCourtDropdown()
                .selectPrice()
                .clickContinueButton();
        bookingDetailsPage
                .selectSplitPayment()
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Friendly private Match booking create with full payment should successful")
    public void verify_friendly_private_match_booking_create_with_full_payment_should_succeed() {
        String searchKeyword = "Farah123";

        initPages();
        userLogin();
        homePage
                .selectMatch();
        bookingPage
                .fillSearchKeyword(searchKeyword)
                .clickBookNowButton()
                .selectBookingTypePrivate()
                .selectGameTypeFriendly()
                .selectBookingDate()
                .selectBookingTime()
                .clickCourtDropdown()
                .selectPrice()
                .clickContinueButton();
        bookingDetailsPage
                .selectFullPayment()
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Cancel booking should successful")
    public void verify_cancel_booking_should_succeed() {
        initPages();
        userLogin();
        homePage
                .clickMyUpcomingBookings();
        myBookingsPage
                .selectBooking();
        bookingDetailsPage
                .clickCancelBookingButton()
                .clickYesToCancelBooking();
    }

    private void userLogin() {
        initPages();
        loginPage
                .fillUserEmail(ConfigReader.get("user.email"))
                .fillUserPassword(ConfigReader.get("user.password"))
                .clickNextButton();
    }
}

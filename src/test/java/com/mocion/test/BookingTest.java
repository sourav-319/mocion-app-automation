package com.mocion.test;

import org.mocion.app.pages.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest {
    private static final String CARD_NUMBER = "4111 1111 1111 1111";
    private static final String CVV_NUMBER = "123";
    private static final String USER_EMAIL = "arifultester@maildrop.cc";
    private static final String USER_PASSWORD = "12345678";

    public LoginPage loginPage;
    public HomePage homePage;
    public BookingPage bookingPage;
    public BookingDetailsPage bookingDetailsPage;
    public PaymentPage paymentPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        bookingPage = new BookingPage(driver);
        bookingDetailsPage = new BookingDetailsPage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test(description = "Friendly public Match booking create should successful")
    public void verify_friendly_public_match_booking_create_should_succeed() throws InterruptedException {
        String searchKeyword = "Club 1";

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
                .fillCardNumber(CARD_NUMBER)
                .fillCVVNumber(CVV_NUMBER)
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Competitive public Match booking create should successful")
    public void verify_competitive_public_match_booking_create_should_succeed() throws InterruptedException {
        String searchKeyword = "Club 1";

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
                .fillCardNumber(CARD_NUMBER)
                .fillCVVNumber(CVV_NUMBER)
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Friendly private Match booking create should successful")
    public void verify_friendly_private_match_booking_create_should_succeed() throws InterruptedException {
        String searchKeyword = "Club 1";

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
                .selectPrice();
        bookingDetailsPage
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber(CARD_NUMBER)
                .fillCVVNumber(CVV_NUMBER)
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Competitive private Match booking create should successful")
    public void verify_competitive_private_match_booking_create_should_succeed() throws InterruptedException {
        String searchKeyword = "Club 1";

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
                .selectPrice();
        bookingDetailsPage
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber(CARD_NUMBER)
                .fillCVVNumber(CVV_NUMBER)
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    private void userLogin() {
        initPages();
        loginPage
                .fillUserEmail(USER_EMAIL)
                .fillUserPassword(USER_PASSWORD)
                .clickNextButton();
    }
}

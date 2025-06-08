package com.mocion.test;

import org.mocion.app.pages.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public BookingPage bookingPage;
    public BookingDetailsPage bookingDetailsPage;
    public PaymentPage paymentPage;

    @Test(description = "Booking create should successful")
    public void verify_booking_create_should_succeed() throws InterruptedException {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        bookingPage = new BookingPage(driver);
        bookingDetailsPage = new BookingDetailsPage(driver);
        paymentPage = new PaymentPage(driver);

        loginPage
                .fillUserEmail()
                .fillUserPassword()
                .clickNextButton();
        homePage
                .selectMatch();
        bookingPage
                .clickBookNowButton()
                .selectBookingDate()
                .selectBookingTime()
                .clickCourtDropdown()
                .selectPrice();
        bookingDetailsPage
                .clickConfirmPaymentButton();
        paymentPage
                .fillCardNumber()
                .fillCVVNumber()
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }
}

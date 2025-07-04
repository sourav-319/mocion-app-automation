package com.mocion.test;

import org.mocion.app.pages.*;
import org.mocion.app.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TournamentsTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public PaymentPage paymentPage;
    public CompetitivePage competitivePage;
    public TournamentsPage tournamentsPage;
    public FriendlyPage friendlyPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
        competitivePage = new CompetitivePage(driver);
        tournamentsPage = new TournamentsPage(driver);
        friendlyPage = new FriendlyPage(driver);
    }

    @Test(description = "Public competitive knockout tournament booking create should successful")
    public void verify_public_competitive_knockout_tournament_booking_create_should_succeed() {
        String searchKeyword = "test_tournament_002";

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .clickTournamentsButton()
                .fillSearchKeyword(searchKeyword)
                .selectTournament();
        tournamentsPage
                .clickBookPlaceButton()
                .clickConfirmTotalPaymentButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Public Friendly knockout tournament booking create should successful")
    public void verify_public_friendly_knockout_tournament_booking_create_should_succeed() {
        String searchKeyword = "test_tournament_002";

        initPages();
        userLogin();
        homePage
                .selectFriendly();
        friendlyPage
                .clickTournamentsButton()
                .fillSearchKeyword(searchKeyword)
                .selectTournament();
        tournamentsPage
                .clickBookPlaceButton()
                .clickConfirmTotalPaymentButton();
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

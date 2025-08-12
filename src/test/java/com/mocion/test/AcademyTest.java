package com.mocion.test;

import org.mocion.app.pages.*;
import org.mocion.app.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AcademyTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public CoachingPage coachingPage;
    public AcademyDetailsPage academyDetailsPage;
    public PaymentPage paymentPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        coachingPage = new CoachingPage(driver);
        academyDetailsPage = new AcademyDetailsPage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test(description = "Academy price displays correctly on academy details page should successful")
    public void verify_academy_price_displays_correctly_on_academy_details_page_should_succeed() {
        String academyPrice = "5.0 AED";
        String searchKeyword = "test_courts_692";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .fillSearchKeyword(searchKeyword)
                .clickViewDetailsButton();

        Assert.assertTrue(academyDetailsPage.getAcademyPriceTextFromDetails().contains(academyPrice));
    }

    @Test(description = "Academy price displays correctly on subscription button should successful")
    public void verify_academy_price_displays_correctly_on_subscription_button_should_succeed() {
        String academyPrice = "5.17 AED";
        String searchKeyword = "test_courts_692";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .fillSearchKeyword(searchKeyword)
                .clickViewDetailsButton();

        Assert.assertTrue(academyDetailsPage.getSubscribeByPaymentButtonText().contains(academyPrice));
    }

    @Test(description = "Academy price displays correctly on telr payment gateway should successful")
    public void verify_academy_price_displays_correctly_on_telr_payment_gateway_should_succeed() {
        String academyPrice = "5.17";
        String searchKeyword = "test_courts_692";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .fillSearchKeyword(searchKeyword)
                .clickViewDetailsButton();
        academyDetailsPage
                .clickSubscribeByPaymentButton()
                .clickConfirmPaymentInfoButton();

        Assert.assertTrue(paymentPage.getAcademyPriceText().contains(academyPrice));
    }

    private void userLogin() {
        initPages();
        loginPage
                .fillUserEmail(ConfigReader.get("user.email"))
                .fillUserPassword(ConfigReader.get("user.password"))
                .clickNextButton();
    }
}

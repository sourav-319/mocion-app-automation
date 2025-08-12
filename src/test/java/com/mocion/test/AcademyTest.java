package com.mocion.test;

import org.mocion.app.pages.*;
import org.mocion.app.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AcademyTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public CoachingPage coachingPage;
    public AcademyDetailsPage academyDetailsPage;
    public PaymentPage paymentPage;
    public NotificationPage notificationPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        coachingPage = new CoachingPage(driver);
        academyDetailsPage = new AcademyDetailsPage(driver);
        paymentPage = new PaymentPage(driver);
        notificationPage = new NotificationPage(driver);
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

    @Test(description = "Academy payment reminder notification receive should successful")
    public void verify_academy_payment_reminder_notification_receive_should_succeed() {
        initPages();
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.academyPaymentReminderNotificationLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Complete the payment process for academy booking should successful")
    public void verify_complete_the_payment_process_for_academy_booking_should_succeed() {
        String searchKeyword = "test number";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .fillSearchKeyword(searchKeyword)
                .clickBookNowButton();
        academyDetailsPage
                .clickSubscribeByPaymentButton()
                .clickConfirmPaymentInfoButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Players appear in the right place in academy details should successful")
    public void verify_players_appear_in_the_right_place_in_academy_details_should_succeed() {
        String searchKeyword = "test number";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .fillSearchKeyword(searchKeyword)
                .clickViewDetailsButton();

        WebElement successElement = academyDetailsPage.playersListLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Cancel button disappears when cancel the subscription to academy should successful")
    public void verify_cancel_button_disappears_when_cancel_the_subscription_to_academy_should_succeed() {
        String searchKeyword = "test number";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .fillSearchKeyword(searchKeyword)
                .clickViewDetailsButton();
        academyDetailsPage
                .clickCancelAcademySubscriptionButton();
        coachingPage
                .clickViewDetailsButton();

        WebElement successElement = academyDetailsPage.subscribeByPaymentButtonLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Public indoor academy booking should successful")
    public void verify_public_indoor_academy_booking_should_succeed() {
        String searchKeyword = "test number";

        initPages();
        userLogin();
        homePage
                .selectCoaching();
        coachingPage
                .fillSearchKeyword(searchKeyword)
                .clickBookNowButton();
        academyDetailsPage
                .clickSubscribeByPaymentButton()
                .clickConfirmPaymentInfoButton();
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

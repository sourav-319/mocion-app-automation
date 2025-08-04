package com.mocion.test;

import org.mocion.app.pages.*;
import org.mocion.app.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PublicEventTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public BookingPage bookingPage;
    public CompetitivePage competitivePage;
    public EventDetailsPage eventDetailsPage;
    public NotificationPage notificationPage;
    public BookingDetailsPage bookingDetailsPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        bookingPage = new BookingPage(driver);
        competitivePage = new CompetitivePage(driver);
        eventDetailsPage = new EventDetailsPage(driver);
        notificationPage = new NotificationPage(driver);
        bookingDetailsPage = new BookingDetailsPage(driver);
    }

    @Test(description = "Public competitive event booking should successful")
    public void verify_public_competitive_event_booking_should_succeed() {
        String searchKeyword = "test rounds";

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickBookPlaceButton();
        bookingDetailsPage
                .clickConfirmTotalPaymentButton();

        WebElement successElement = bookingDetailsPage.confirmBookingSuccessMessageLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Request to join waiting list for competitive public event should successful")
    public void verify_request_to_join_waiting_list_for_competitive_public_event_should_succeed() {
        String searchKeyword = "test rounds";

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickAddMeToWaitingListButton();

        WebElement successElement = eventDetailsPage.addMeToWaitingListSuccessLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Cancel request to join waiting list for competitive public event should successful")
    public void verify_cancel_request_to_join_waiting_list_for_competitive_public_event_should_succeed() {
        String searchKeyword = "test rounds";

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickCancelInscriptionButton()
                .clickYestToConfirmCancelBooking();

        WebElement successElement = eventDetailsPage.cancelInscriptionSuccessLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Cancel public competitive event booking should successful")
    public void verify_cancel_public_competitive_event_booking_should_succeed() {
        String searchKeyword = "test rounds";

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickCancelInscriptionButton()
                .clickYestToConfirmCancelBooking();

        WebElement successElement = eventDetailsPage.cancelInscriptionSuccessLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Add player to event send notification should successful")
    public void verify_add_player_to_event_send_notification_should_succeed() {
        initPages();
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.addPlayerToEventNotificationLocator();
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

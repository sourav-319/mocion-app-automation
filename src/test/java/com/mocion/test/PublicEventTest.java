package com.mocion.test;

import org.mocion.app.pages.*;
import org.mocion.app.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class PublicEventTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public BookingPage bookingPage;
    public CompetitivePage competitivePage;
    public EventDetailsPage eventDetailsPage;
    public NotificationPage notificationPage;
    public BookingDetailsPage bookingDetailsPage;
    public PaymentPage paymentPage;
    public FriendlyPage friendlyPage;

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        bookingPage = new BookingPage(driver);
        competitivePage = new CompetitivePage(driver);
        eventDetailsPage = new EventDetailsPage(driver);
        notificationPage = new NotificationPage(driver);
        bookingDetailsPage = new BookingDetailsPage(driver);
        paymentPage = new PaymentPage(driver);
        friendlyPage = new FriendlyPage(driver);
    }

    @Test(description = "Public competitive event booking should successful")
    public void verify_public_competitive_event_booking_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 3";

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
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Right buttons appear for the user depending on his status for public competitive event should successful")
    public void verify_right_buttons_appear_for_the_user_depending_on_his_status_for_public_competitive_event_should_succeed() {
        String searchKeyword = "test rounds";

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();

        WebElement successElement = eventDetailsPage.addMeToWaitingListButtonLocator();
        Assert.assertTrue(successElement.isDisplayed());

        eventDetailsPage
                .clickAddMeToWaitingListButton();

        WebElement successElementTwo = eventDetailsPage.cancelInscriptionButtonLocator();
        Assert.assertTrue(successElementTwo.isDisplayed());

        eventDetailsPage
                .clickCancelInscriptionButton()
                .clickYestToConfirmCancelBooking();

        WebElement successElementThree = eventDetailsPage.addMeToWaitingListButtonLocator();
        Assert.assertTrue(successElementThree.isDisplayed());
    }

    @Test(description = "Request to join waiting list for public competitive event should successful")
    public void verify_request_to_join_waiting_list_for_public_competitive_event_should_succeed() {
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

    @Test(description = "Remove me from waiting list for public competitive event should successful")
    public void verify_remove_me_from_waiting_list_for_public_competitive_event_should_succeed() {
        String searchKeyword = "test rounds";

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickRemoveMeFromWaitingListButton();

        WebElement successElement = eventDetailsPage.bookPlaceButtonLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Cancel public competitive event after join should successful")
    public void verify_cancel_public_competitive_event_after_cancel_should_succeed() {
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

    @Test(description = "Add player to public competitive event send notification should successful")
    public void verify_add_player_to_public_competitive_event_send_notification_should_succeed() {
        initPages();
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.addPlayerToEventNotificationLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Waiting list player get notification when public competitive event player cancel booking should successful")
    public void verify_waiting_list_player_get_notification_when_public_competitive_event_player_cancel_booking_should_succeed() {
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.eventWaitingListNotificationLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Waiting list player get notification when club admin remove player from public competitive event should successful")
    public void verify_waiting_list_player_get_notification_when_club_admin_remove_player_from_public_competitive_event_should_succeed() {
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.eventWaitingListNotificationLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Player should not repeat a partner until they match with all other players in public competitive americano event should successful")
    public void verify_player_should_not_repeat_a_partner_until_they_match_with_all_other_players_in_public_competitive_americano_event_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 2";
        int totalRounds = 5;

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickScheduleIcon();

        Map<String, Map<String, List<List<String>>>> roundsData = eventDetailsPage.getAllRoundsData(totalRounds);
        eventDetailsPage.verifyNoPartnerRepeatUntilAllMatched(roundsData);
    }

    @Test(description = "Not to repeat the opponent as long as possible in public competitive americano event should successful")
    public void verify_not_to_repeat_the_opponent_as_long_as_possible_in_public_competitive_americano_event_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 2";
        int totalRounds = 5;

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickScheduleIcon();

        Map<String, Map<String, List<List<String>>>> roundsData = eventDetailsPage.getAllRoundsData(totalRounds);
        eventDetailsPage.verifyNoOpponentRepeat(roundsData);
    }

    @Test(description = "Repeat the opponent with the least matches played in public competitive americano event should successful")
    public void verify_repeat_the_opponent_with_the_least_matches_played_in_public_competitive_americano_event_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 2";
        int totalRounds = 5;

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickScheduleIcon();

        Map<String, Map<String, List<List<String>>>> roundsData = eventDetailsPage.getAllRoundsData(totalRounds);
        eventDetailsPage.verifyOpponentRepeatWithLeastPlayed(roundsData);
    }

    @Test(description = "Not to play against player in consecutive rounds in public competitive americano event should successful")
    public void verify_not_to_play_against_player_in_consecutive_rounds_in_public_competitive_americano_event_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 2";
        int totalRounds = 5;

        initPages();
        userLogin();
        homePage
                .selectCompetitive();
        competitivePage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickScheduleIcon();

        Map<String, Map<String, List<List<String>>>> roundsData = eventDetailsPage.getAllRoundsData(totalRounds);
        eventDetailsPage.verifyNoConsecutiveOpponents(roundsData);
    }

    @Test(description = "Player cancels Telr payment after joining an event with payment type app is automatically removed from the event after ten minutes should successful")
    public void verify_player_cancels_telr_payment_after_joining_an_event_with_payment_type_app_is_automatically_removed_from_the_event_after_ten_minutes_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 3";

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
        paymentPage
                .clickCancelPaymentButton()
                .clickOkButton();
        bookingDetailsPage
                .clickBackIcon();
        competitivePage
                .waitSafely(10);
        competitivePage
                .selectPublicEvent();
        eventDetailsPage
                .swipeToSeeAllPlayers(5);

        Assert.assertFalse(eventDetailsPage.isEventPlayerDisplayed());
    }

    @Test(description = "Event with payment type club keeps player in event if they paid from app should successful")
    public void verify_event_with_payment_type_club_keeps_player_in_event_if_they_paid_from_app_should_succeed() {
        String searchKeyword = "test auto cancel";

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
                .clickConfirmTotalPaymentButton()
                .clickOkButton();
        eventDetailsPage
                .swipeToSeeAllPlayers(5);

        WebElement successElement = eventDetailsPage.eventPlayerLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Public friendly event booking should successful")
    public void verify_public_friendly_event_booking_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 3";

        initPages();
        userLogin();
        homePage
                .selectFriendly();
        friendlyPage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickBookPlaceButton();
        bookingDetailsPage
                .clickConfirmTotalPaymentButton();
        paymentPage
                .fillCardNumber(ConfigReader.get("card_number"))
                .fillCVVNumber(ConfigReader.get("cvv_number"))
                .clickMakePaymentButton()
                .clickAcceptPaymentButton();

        WebElement successElement = paymentPage.waitForPaymentSuccessElement();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Right buttons appear for the user depending on his status for public friendly event should successful")
    public void verify_right_buttons_appear_for_the_user_depending_on_his_status_for_public_friendly_event_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 3";

        initPages();
        userLogin();
        homePage
                .selectFriendly();
        friendlyPage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();

        WebElement successElement = eventDetailsPage.addMeToWaitingListButtonLocator();
        Assert.assertTrue(successElement.isDisplayed());

        eventDetailsPage
                .clickAddMeToWaitingListButton();

        WebElement successElementTwo = eventDetailsPage.removeMeFromWaitingListButtonLocator();
        Assert.assertTrue(successElementTwo.isDisplayed());

        eventDetailsPage
                .clickRemoveMeFromWaitingListButton();

        WebElement successElementThree = eventDetailsPage.addMeToWaitingListButtonLocator();
        Assert.assertTrue(successElementThree.isDisplayed());
    }

    @Test(description = "Request to join waiting list for public friendly event should successful")
    public void verify_request_to_join_waiting_list_for_public_friendly_event_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 3";

        initPages();
        userLogin();
        homePage
                .selectFriendly();
        friendlyPage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickAddMeToWaitingListButton();

        WebElement successElement = eventDetailsPage.addMeToWaitingListSuccessLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Remove me from waiting list for public friendly event should successful")
    public void verify_remove_me_from_waiting_list_for_public_friendly_event_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 3";

        initPages();
        userLogin();
        homePage
                .selectFriendly();
        friendlyPage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickRemoveMeFromWaitingListButton();

        WebElement successElement = eventDetailsPage.bookPlaceButtonLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Cancel public friendly event after join should successful")
    public void verify_cancel_public_friendly_event_after_cancel_should_succeed() {
        String searchKeyword = "test rounds logic 3/8 3";

        initPages();
        userLogin();
        homePage
                .selectFriendly();
        friendlyPage
                .fillSearchKeyword(searchKeyword)
                .selectPublicEvent();
        eventDetailsPage
                .clickCancelInscriptionButton()
                .clickYestToConfirmCancelBooking();

        WebElement successElement = eventDetailsPage.cancelInscriptionSuccessLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Add player to public friendly event send notification should successful")
    public void verify_add_player_to_public_friendly_event_send_notification_should_succeed() {
        initPages();
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.addPlayerToEventNotificationLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Waiting list player get notification when public friendly event player cancel booking should successful")
    public void verify_waiting_list_player_get_notification_when_public_friendly_event_player_cancel_booking_should_succeed() {
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.eventWaitingListNotificationLocator();
        Assert.assertTrue(successElement.isDisplayed());
    }

    @Test(description = "Waiting list player get notification when club admin remove player from public friendly event should successful")
    public void verify_waiting_list_player_get_notification_when_club_admin_remove_player_from_public_friendly_event_should_succeed() {
        userLogin();
        homePage
                .clickNotificationIcon();

        WebElement successElement = notificationPage.eventWaitingListNotificationLocator();
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

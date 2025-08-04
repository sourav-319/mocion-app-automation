package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EventDetailsPage extends BasePage {
    private static final String EVENT_DETAILS_SCREEN = "event_details_screen";

    public EventDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public EventDetailsPage clickCancelInscriptionButton() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "cancel_inscription_button");
        click(EVENT_DETAILS_SCREEN, "cancel_inscription_button");
        return this;
    }

    public void clickRemoveMeFromWaitingListButton() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "remove_me_from_waiting_list_button");
        click(EVENT_DETAILS_SCREEN, "remove_me_from_waiting_list_button");
    }

    public void clickBookPlaceButton() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "book_place_button");
        click(EVENT_DETAILS_SCREEN, "book_place_button");
    }

    public void clickYestToConfirmCancelBooking() {
        click(EVENT_DETAILS_SCREEN, "yes_to_confirm_cancel_booking");
    }

    public void clickAddMeToWaitingListButton() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "add_me_to_waiting_list_button");
        click(EVENT_DETAILS_SCREEN, "add_me_to_waiting_list_button");
    }

    public WebElement addMeToWaitingListSuccessLocator() {
        By locator = getLocator(EVENT_DETAILS_SCREEN, "add_me_to_waiting_list_success_message");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement cancelInscriptionSuccessLocator() {
        By locator = getLocator(EVENT_DETAILS_SCREEN, "cancel_inscription_success_message");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement bookPlaceButtonLocatorLocator() {
        By locator = getLocator(EVENT_DETAILS_SCREEN, "book_place_button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

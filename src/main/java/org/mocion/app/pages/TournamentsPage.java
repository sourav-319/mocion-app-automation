package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class TournamentsPage extends BasePage {
    private static final String TOURNAMENTS_SCREEN = "tournaments_screen";

    public TournamentsPage(AppiumDriver driver) {
        super(driver);
    }

    public TournamentsPage clickBookPlaceButton() {
        scrollUntilVisible(TOURNAMENTS_SCREEN, "book_place_button");
        click(TOURNAMENTS_SCREEN, "book_place_button");
        return this;
    }

    public void clickConfirmTotalPaymentButton() {
        click(TOURNAMENTS_SCREEN, "confirm_total_payment_button");
    }
}

package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class CoachDetailsPage extends BasePage {
    private static final String COACH_DETAILS_SCREEN = "coach_details_screen";

    public CoachDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public CoachDetailsPage selectBookingTypePrivateCoachingPackage() {
        click(COACH_DETAILS_SCREEN, "booking_type_dropdown");
        click(COACH_DETAILS_SCREEN, "booking_type_private_coaching_package");
        return this;
    }

    public CoachDetailsPage selectCourtTypeIndoor() {
        click(COACH_DETAILS_SCREEN, "court_type_indoor");
        return this;
    }

    public CoachDetailsPage selectCourtTypeOutdoor() {
        click(COACH_DETAILS_SCREEN, "court_type_outdoor");
        return this;
    }

    public void clickBuyNowButton() {
        click(COACH_DETAILS_SCREEN, "buy_now_button");
    }
}

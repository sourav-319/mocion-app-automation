package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class AcademyDetailsPage extends BasePage {
    private static final String ACADEMY_DETAILS_SCREEN = "academy_details_screen";

    public AcademyDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public AcademyDetailsPage clickSubscribeByPaymentButton() {
        scrollUntilVisible(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
        click(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
        return this;
    }

    public void clickConfirmPaymentInfoButton() {
        click(ACADEMY_DETAILS_SCREEN, "confirm_payment_info_button");
    }

    public String getSubscribeByPaymentButtonText() {
        scrollUntilVisible(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
        return getText(ACADEMY_DETAILS_SCREEN, "subscribe_by_payment_button");
    }

    public String getAcademyPriceTextFromDetails() {
        return getText(ACADEMY_DETAILS_SCREEN, "academy_price_text");
    }
}

package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class PackageDetailsPage extends BasePage {
    private static final String PACKAGE_DETAILS_SCREEN = "package_details_screen";

    public PackageDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickBuyNowButton() {
        scrollUntilVisible(PACKAGE_DETAILS_SCREEN, "buy_now_button");
        click(PACKAGE_DETAILS_SCREEN, "buy_now_button");
    }
}

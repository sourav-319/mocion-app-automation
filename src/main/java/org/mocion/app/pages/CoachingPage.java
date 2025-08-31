package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CoachingPage extends BasePage {
    private static final String COACHING_SCREEN = "coaching_screen";

    public CoachingPage(AppiumDriver driver) {
        super(driver);
    }

    public CoachingPage fillSearchKeyword(String searchKeyword) {
        click(COACHING_SCREEN, "search_input_field");
        type(COACHING_SCREEN, "search_input_field", searchKeyword);
        ((AndroidDriver) driver).hideKeyboard();
        return this;
    }

    public CoachingPage selectCoaches() {
        click(COACHING_SCREEN, "coaches_button");
        return this;
    }

    public void clickBuyAPackageButton() {
        click(COACHING_SCREEN, "buy_a_package_button");
    }

    public void clickViewDetailsButton() {
        click(COACHING_SCREEN, "view_details_button");
    }

    public void clickBookNowButton() {
        click(COACHING_SCREEN, "book_now_button");
    }
}

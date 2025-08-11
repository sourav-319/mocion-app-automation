package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CoachingPage extends BasePage {
    private static final String COACHING_SCREEN = "coaching_screen";

    public CoachingPage(AppiumDriver driver) {
        super(driver);
    }

    public CoachingPage fillSearchKeyword(String searchKeyword) {
        click(COACHING_SCREEN, "search_input_field");
        type(COACHING_SCREEN, "search_input_field", searchKeyword);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        return this;
    }

    public void clickViewDetailsButton() {
        click(COACHING_SCREEN, "view_details_button");
    }
}

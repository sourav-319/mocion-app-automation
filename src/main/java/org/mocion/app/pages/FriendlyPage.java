package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class FriendlyPage extends BasePage {
    private static final String FRIENDLY_SCREEN = "friendly_screen";

    public FriendlyPage(AppiumDriver driver) {
        super(driver);
    }

    public FriendlyPage fillSearchKeyword(String searchKeyword) {
        click(FRIENDLY_SCREEN, "search_input_field");
        type(FRIENDLY_SCREEN, "search_input_field", searchKeyword);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        return this;
    }

    public FriendlyPage clickTournamentsButton() {
        click(FRIENDLY_SCREEN, "click_tournaments");
        return this;
    }

    public void selectPublicEvent() {
        click(FRIENDLY_SCREEN, "select_public_event");
    }

    public void selectTournament() {
        click(FRIENDLY_SCREEN, "select_tournament");
    }
}

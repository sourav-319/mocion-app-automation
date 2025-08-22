package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CompetitivePage extends BasePage {
    private static final String COMPETITIVE_SCREEN = "competitive_screen";

    public CompetitivePage(AppiumDriver driver) {
        super(driver);
    }

    public CompetitivePage fillSearchKeyword(String searchKeyword) {
        click(COMPETITIVE_SCREEN, "search_input_field");
        type(COMPETITIVE_SCREEN, "search_input_field", searchKeyword);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        return this;
    }

    public CompetitivePage clickTournamentsButton() {
        click(COMPETITIVE_SCREEN, "click_tournaments");
        return this;
    }

    public void selectTournament() {
        click(COMPETITIVE_SCREEN, "select_tournament");
    }

    public void selectPublicEvent() {
        click(COMPETITIVE_SCREEN, "select_public_event");
    }

    public void waitSafely(int minutes) {
        long endTime = System.currentTimeMillis() + minutes * 60_000L;
        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(30_000);
                driver.getPageSource();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

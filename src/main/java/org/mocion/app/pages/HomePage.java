package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage {
    private static final String HOME_SCREEN = "home_screen";

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void selectMatch() {
        click(HOME_SCREEN, "select_match");
    }
}

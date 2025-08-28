package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage {
    private static final String LOGIN_SCREEN = "login_screen";

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage fillUserEmail(String userEmail) {
        click(LOGIN_SCREEN, "email_field");
        type(LOGIN_SCREEN, "email_field", userEmail);
        ((AndroidDriver) driver).hideKeyboard();
        return this;
    }

    public LoginPage fillUserPassword(String userPassword) {
        click(LOGIN_SCREEN, "password_field");
        type(LOGIN_SCREEN, "password_field", userPassword);
        ((AndroidDriver) driver).hideKeyboard();
        return this;
    }

    public void clickNextButton() {
        click(LOGIN_SCREEN, "next_button");
    }
}

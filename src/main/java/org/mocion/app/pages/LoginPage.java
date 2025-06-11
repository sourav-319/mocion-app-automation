package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class LoginPage extends BasePage {
    private static final String LOGIN_SCREEN = "login_screen";

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage fillUserEmail() {
        click(LOGIN_SCREEN, "email_field");
        type(LOGIN_SCREEN, "email_field", "arifultester@maildrop.cc");
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        return this;
    }

    public LoginPage fillUserPassword() {
        click(LOGIN_SCREEN, "password_field");
        type(LOGIN_SCREEN, "password_field", "12345678");
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        return this;
    }

    public void clickNextButton() {
        click(LOGIN_SCREEN, "next_button");
    }
}

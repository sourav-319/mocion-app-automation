package com.mocion.test;

import io.appium.java_client.AppiumDriver;
import org.mocion.app.drivers.DriverFactory;
import org.mocion.app.enums.PlatformType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {
    protected AppiumDriver driver;

    @BeforeSuite
    public void setup() {
        //AppiumServerManager.startServer(4723);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initializeDriver(PlatformType.ANDROID);
        driver = DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quit();
    }

    @AfterSuite
    public void teardown() {
        //AppiumServerManager.stopServer();
    }
}

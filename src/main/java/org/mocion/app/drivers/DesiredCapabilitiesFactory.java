package org.mocion.app.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesFactory {
    public static DesiredCapabilities getCapabilitiesForVersion(String androidVersion) {
        return new DesiredCapabilities();
    }
}

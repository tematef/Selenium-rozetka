package com.rozetka.selenium.utils;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by artem on 3/24/17.
 */
class ProfileSetup {

    static DesiredCapabilities createChromeConfig() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("-disable-cache");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    static String getChromeDriverPath() {
        String path = System.getProperty("user.dir") + File.separator + "drivers" + File.separator;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return path + "chromedriver_win.exe";
        }
        if (os.contains("mac")) {
            return path + "chromedriver_mac";
        }
        if (os.contains("linux")) {
            return path + "chromedriver_linux";
        }
        throw new InvalidArgumentException("OS is not on the list");
    }
}

package com.rozetka.selenium.utils;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.io.File;

/**
 * Created by artem on 3/24/17.
 */
public class ProfileSetup {

    public static FirefoxProfile setFirefoxProfile() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.dir", getPathToFile(""));
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "video/3gpp,video/mp4,audio/x-wav,audio/mp4a-latm,image/png,text/comma-separated-values," +
                        "text/csv,application/csv,application/excel,application/vnd.ms-excel,application/vnd.msexcel," +
                        "text/anytext,text/html,application/xhtml+xml,application/xml,binary/octet-stream");
        return firefoxProfile;
    }

    public static String getPathToFile(String fileName) {
        return System.getProperty("java.io.tmpdir") + fileName;
    }

//    public static DesiredCapabilities createChromeConfig() {
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        options.addArguments("-disable-cache");
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_settings.popups", 0);
//        prefs.put("download.directory_upgrade", true);
//        prefs.put("prompt_for_download", false);
//        prefs.put("download.prompt_for_download", false);
//        prefs.put("download.extensions_to_open", "");
//        prefs.put("default_directory", "/tmp");
//        prefs.put("download.default_directory", "/tmp");
//        prefs.put("savefile.default_directory", "/tmp");
//        options.setExperimentalOption("prefs", prefs);
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        return capabilities;
//    }
//
//    static String getChromeDriverPath() {
//        String path = System.getProperty("user.dir") + File.separator + "drivers" + File.separator;
//        String os = System.getProperty("os.name").toLowerCase();
//        if (os.contains("win")) {
//            return path + "chromedriver_win.exe";
//        }
//        if (os.contains("mac")) {
//            return path + "chromedriver_mac";
//        }
//        if (os.contains("linux")) {
//            return path + "chromedriver_linux";
//        }
//        throw new InvalidArgumentException("OS is not on the list");
//    }

    public static String getGeckoDriverPath() {
        String path = System.getProperty("user.dir") + File.separator + "drivers" + File.separator;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return path + "geckodriver64.exe";
        }
        if (os.contains("mac")) {
            return path + "geckodriver_macos";
        }
        if (os.contains("linux")) {
            return path + "geckodriver_linux64";
        }
        throw new InvalidArgumentException("OS is not on the list");
    }
}

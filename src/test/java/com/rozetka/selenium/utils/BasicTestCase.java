package com.rozetka.selenium.utils;

import com.rozetka.selenium.utils.db.MySqlDb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import static com.rozetka.selenium.utils.ProfileSetup.createChromeConfig;
import static com.rozetka.selenium.utils.ProfileSetup.getChromeDriverPath;

/**
 * Created by artem on 3/24/17.
 */
public abstract class BasicTestCase {

    protected WebDriver driver;
    protected MySqlDb db;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        driver = new ChromeDriver(createChromeConfig());
        db = new MySqlDb();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        db.closeDbConnection();
    }
}

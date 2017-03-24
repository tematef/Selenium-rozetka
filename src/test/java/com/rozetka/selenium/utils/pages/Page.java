package com.rozetka.selenium.utils.pages;

import com.rozetka.selenium.utils.SeleniumProperties;
import com.rozetka.selenium.utils.pages.blocks.PaginationBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * Created by artem on 3/24/17.
 */
public abstract class Page {

    public String hostName = SeleniumProperties.getProperty("selenium.hostname");
    public String pageUrl;

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    public PaginationBlock paginationBlock;

    public Page(WebDriver driver, String url) {
        this.driver = driver;
        this.pageUrl = hostName + url;
        driverWait = new WebDriverWait(driver, 20);
        driverWait.ignoring(StaleElementReferenceException.class);
//        PageFactory.initElements(new HtmlElementLocatorFactory(driver), this);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);

    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void open() {
        driver.get(this.pageUrl);
    }

    public WebDriver getDriver() {
        return driver;
    }


//    public boolean isOpened() {
//        return isPageLoaded(pageUrl);
//    }



//    public boolean isPageLoaded(String url) {
//        int timeToWait = Integer.valueOf(SeleniumProperties.getProperty("page.wait"));
//        try {
//            await().atMost(timeToWait, TimeUnit.SECONDS).until(() -> driver.getCurrentUrl().replace("\"", "%22").replace(" ", "%20").contains(url));
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }


    protected Boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }
}

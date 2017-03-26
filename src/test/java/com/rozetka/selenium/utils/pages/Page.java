package com.rozetka.selenium.utils.pages;

import com.rozetka.selenium.utils.SeleniumProperties;
import com.rozetka.selenium.utils.pages.blocks.PaginationBlock;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * Created by artem on 3/24/17.
 */
public abstract class Page {

    public PaginationBlock paginationBlock;
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    private String pageUrl;

    public Page(WebDriver driver, String url) {
        this.driver = driver;
        this.pageUrl = SeleniumProperties.getProperty("selenium.hostname") + url;
        driverWait = new WebDriverWait(driver, 20);
        driverWait.ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public void refresh() {
        driver.navigate().refresh();
    }
    public WebDriver getDriver() {
        return driver;
    }
    void open() {
        driver.get(this.pageUrl);
    }
}

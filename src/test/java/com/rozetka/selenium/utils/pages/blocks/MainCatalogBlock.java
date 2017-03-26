package com.rozetka.selenium.utils.pages.blocks;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by artem on 3/24/17.
 */
@FindBy(id = "m-main-ul")
public class MainCatalogBlock extends HtmlElement {

    @FindBy(xpath = ".//li[@id='3361']/a")
    private Button smartTvElectronicButton;

    public void clickSmartTvElectronicButton(WebDriver driver) {
        safeClick(smartTvElectronicButton, driver);
    }

    //due to some reasons click on <smartTvElectronicButton> button not always leads for opening next page
    private void safeClick(Button button, WebDriver driver) {
        WebDriverWait driverWait = new WebDriverWait(driver, 5);
        driverWait.ignoring(StaleElementReferenceException.class);
        String url = driver.getCurrentUrl();
        driverWait.until(ExpectedConditions.elementToBeClickable(smartTvElectronicButton.getWrappedElement())).click();
        if (driver.getCurrentUrl().equals(url) && smartTvElectronicButton.isDisplayed()) {
            clickSmartTvElectronicButton(driver);
        }
    }
}

package com.rozetka.selenium.utils.pages.innerpages.telephonespage;

import com.rozetka.selenium.utils.objects.Device;
import com.rozetka.selenium.utils.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by artem on 3/24/17.
 */
public class TelephonesPage extends Page {

    private TelephoneCategoriesBlock telephoneCategoriesBlock;
    private By allItemsOnPage = By.xpath(".//div[@class='g-i-tile-i-box']");

    public TelephonesPage(WebDriver driver) {
        super(driver, "/telefony/c4627900/");
    }

    public void clickSmartPhonesButton() {
        telephoneCategoriesBlock.clickSmartPhonesButton();
    }

    public List<Device> getTopSoldDevices() {
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allItemsOnPage));
        return getTopSoldItems().stream().map(Device::new).collect(Collectors.toList());
    }

    public void sortItemsByPopularity() {
        By sortDropDown = By.cssSelector(".sort-view-link.sprite-side");
        By popularChose = By.id("filter_sortpopularity");
        driverWait.until(ExpectedConditions.elementToBeClickable(sortDropDown));
        driver.findElement(sortDropDown).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(popularChose));
        driver.findElement(popularChose).click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(popularChose));
    }

    private List<WebElement> getTopSoldItems() {
        By topSoldItems = By.xpath(".//i[@class='g-tag g-tag-icon-middle-popularity sprite']/../../..");
        driverWait.until(ExpectedConditions.presenceOfElementLocated(allItemsOnPage));
        return driver.findElements(topSoldItems);
    }
}

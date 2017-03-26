package com.rozetka.selenium.utils.pages.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.awaitility.Awaitility.await;

/**
 * Created by artem on 3/24/17.
 */
@FindBy(xpath = ".//ul[@name='paginator']")
public class PaginationBlock extends HtmlElement {

    @FindBy(xpath = ".//li[@class='paginator-catalog-l-i pos-fix active']")
    private WebElement activePagePaginatorBtn;

    public void openNextPage(WebDriverWait driverWait) {
        int currentPage = getCurrentPage(driverWait);
        By nextPageButton = By.id("page" + (currentPage + 1));
        driverWait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
        findElement(nextPageButton).click();
        await().until(() -> getCurrentPage(driverWait) == currentPage + 1);
    }

    public void openPreviousPage(WebDriverWait driverWait) {
        int currentPagePosition = getCurrentPage(driverWait);
        if (currentPagePosition > 1) {
            findElement(By.id("page" + (getCurrentPage(driverWait) - 1))).click();
        } else throw new IllegalArgumentException("You are one first page");
    }

    private int getCurrentPage(WebDriverWait driverWait) {
//        driverWait.until(ExpectedConditions.visibilityOf(activePagePaginatorBtn));
//        driverWait.until(ExpectedConditions.visibilityOf(activePagePaginatorBtn));
        return Integer.valueOf( driverWait.until(ExpectedConditions.visibilityOf(activePagePaginatorBtn))
        .getAttribute("ID").replaceAll("[^0-9]", ""));
    }
}

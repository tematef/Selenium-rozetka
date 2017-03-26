package com.rozetka.selenium.utils.pages.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.awaitility.Awaitility.await;

/**
 * Created by artem on 3/24/17.
 */
@FindBy(xpath = ".//ul[@name='paginator']")
public class PaginationBlock extends HtmlElement {

    public void openNextPage() {
        By nextPageButton = By.id("page" + (getCurrentPage() + 1));
        await().until(() -> ExpectedConditions.elementToBeClickable(nextPageButton));
        findElement(nextPageButton).click();
    }

    public void openPreviousPage() {
        int currentPagePosition = getCurrentPage();
        if (currentPagePosition > 1) {
            findElement(By.id("page" + (getCurrentPage() - 1))).click();
        } else throw new IllegalArgumentException("You are one first page");
    }

    private int getCurrentPage() {
        By activePagePaginatorButton = By.xpath(".//li[@class='paginator-catalog-l-i pos-fix active']");
        await().until(() -> ExpectedConditions.visibilityOfElementLocated(activePagePaginatorButton));
        WebElement currentPositionPageBtn = findElement(activePagePaginatorButton);
        return Integer.valueOf(currentPositionPageBtn.getAttribute("ID").replaceAll("[^0-9]", ""));
    }
}

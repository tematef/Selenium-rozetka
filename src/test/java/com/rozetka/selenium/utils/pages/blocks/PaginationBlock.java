package com.rozetka.selenium.utils.pages.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by artem on 3/24/17.
 */
@FindBy(xpath = ".//ul[@name='paginator']")
public class PaginationBlock extends HtmlElement {

    public void openNextPage() {
        findElement(By.id("page" + (getCurrentPage() + 1))).click();
    }

    public void openPreviousPage() {
        int currentPagePosition = getCurrentPage();
        if (currentPagePosition > 1) {
            findElement(By.id("page" + (getCurrentPage() - 1))).click();
        } else throw new IllegalArgumentException("You are one first page");
    }

    private int getCurrentPage() {
        WebElement currentPositionPageBtn = findElement(By.xpath(".//li[@class='paginator-catalog-l-i pos-fix active']"));
        return Integer.valueOf(currentPositionPageBtn.getAttribute("ID").replaceAll("[^0-9]", ""));
    }
}

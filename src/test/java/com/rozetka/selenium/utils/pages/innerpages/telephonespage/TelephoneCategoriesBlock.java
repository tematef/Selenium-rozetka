package com.rozetka.selenium.utils.pages.innerpages.telephonespage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.awaitility.Awaitility.await;

/**
 * Created by artem on 3/24/17.
 */
@FindBy(xpath = ".//ul[@id='menu_categories_left']/li[1]")
public class TelephoneCategoriesBlock extends HtmlElement {

    @FindBy(xpath = ".//a[text()='Смартфоны']")
    private Button smartPhonesButton;

    void clickSmartPhonesButton() {
        await().until(() -> ExpectedConditions.elementToBeClickable(smartPhonesButton.getWrappedElement()));
        smartPhonesButton.click();
    }
}

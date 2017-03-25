package com.rozetka.selenium.utils.pages.blocks;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.awaitility.Awaitility.await;

/**
 * Created by artem on 3/24/17.
 */
@FindBy(id = "m-main-ul")
public class MainCatalogBlock extends HtmlElement {

    @FindBy(xpath = ".//li[@id='3361']/a")
    private Button smartTvElectronicButton;

    public void clickSmartTvElectronicButton() {
        await().until(() -> ExpectedConditions.elementToBeClickable(smartTvElectronicButton.getWrappedElement()));
        smartTvElectronicButton.click();
    }
}

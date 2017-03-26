package com.rozetka.selenium.utils.pages.innerpages.smarttvelectronicpage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.awaitility.Awaitility.await;

/**
 * Created by artem on 3/24/17.
 */

@FindBy(id = "menu_categories_left")
public class SmartTvElectronicLeftNavBlock extends HtmlElement {

    @FindBy(xpath = ".//a[text()='Телефоны']")
    private Button telephonesButton;

    void clickTelephonesButton() {
        await().until(() -> ExpectedConditions.elementToBeClickable(telephonesButton.getWrappedElement()));
        telephonesButton.click();
    }
}

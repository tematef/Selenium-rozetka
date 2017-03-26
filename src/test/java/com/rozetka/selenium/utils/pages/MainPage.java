package com.rozetka.selenium.utils.pages;

import com.rozetka.selenium.utils.pages.blocks.MainCatalogBlock;
import com.rozetka.selenium.utils.pages.innerpages.smarttvelectronicpage.SmartTvElectronicPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;

/**
 * Created by artem on 3/24/17.
 */
public class MainPage extends Page {

    private MainCatalogBlock mainCatalogBlock;

    @FindBy(xpath = ".//a[@href='/']")
    private Link logoLink;

    @FindBy(id = "help-link")
    private Link helpLink;

    @FindBy(css = ".page-container>div")
    private TextBlock titleTextTB;

    @FindBy(xpath = ".//i[@class='fa fa-user']")
    private Link myAccountLink;

    @FindBy(css = "a[href=\"/index/logout\"]")
    private Link logOutLink;

    @FindBy(id = "navbar-uses-cases-button")
    private Button freeCreditsButton;

    public MainPage(WebDriver driver) {
        super(driver, "/");
    }

    public static MainPage navigateTo(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        return mainPage;
    }

    public SmartTvElectronicPage navigateToSmartTvElectronicPage() {
        mainCatalogBlock.clickSmartTvElectronicButton(driver);
        return new SmartTvElectronicPage(driver);
    }
}

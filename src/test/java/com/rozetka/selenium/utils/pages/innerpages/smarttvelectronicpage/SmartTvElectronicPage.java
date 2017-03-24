package com.rozetka.selenium.utils.pages.innerpages.smarttvelectronicpage;

import com.rozetka.selenium.utils.pages.Page;

import com.rozetka.selenium.utils.pages.innerpages.telephonespage.TelephonesPage;
import org.openqa.selenium.WebDriver;


/**
 * Created by artem on 3/24/17.
 */
public class SmartTvElectronicPage extends Page {

    private SmartTvElectronicLeftNavBlock smartTvElectronicLeftNavBlock;

    public SmartTvElectronicPage(WebDriver driver) {
        super(driver, "/telefony-tv-i-ehlektronika/c4627949/");
    }

    public TelephonesPage clickTelephonesButton() {
        smartTvElectronicLeftNavBlock.clickTelephonesButton();
        return new TelephonesPage(driver);
    }
}

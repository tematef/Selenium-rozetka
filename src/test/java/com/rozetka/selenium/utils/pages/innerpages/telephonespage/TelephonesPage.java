package com.rozetka.selenium.utils.pages.innerpages.telephonespage;

import com.rozetka.selenium.utils.pages.Page;
import org.openqa.selenium.WebDriver;

/**
 * Created by artem on 3/24/17.
 */
public class TelephonesPage extends Page {

    private TelephoneCategoriesBlock telephoneCategoriesBlock;

    public TelephonesPage(WebDriver driver) {
        super(driver, "/telefony/c4627900/");
    }

    public void clickSmartPhonesButton() {
        telephoneCategoriesBlock.clickSmartPhonesButton();
    }


}

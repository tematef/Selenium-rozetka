package com.rozetka.selenium.tests;

import com.rozetka.selenium.utils.BasicTestCase;
import com.rozetka.selenium.utils.pages.MainPage;
import com.rozetka.selenium.utils.pages.innerpages.smarttvelectronicpage.SmartTvElectronicPage;
import com.rozetka.selenium.utils.pages.innerpages.telephonespage.TelephonesPage;
import org.testng.annotations.Test;

/**
 * Created by artem on 3/24/17.
 */
public class TestGetDeviceNames extends BasicTestCase {

    @Test
    public void test() {
        MainPage mainPage = MainPage.navigateTo(driver);
        SmartTvElectronicPage smartTvElectronicPage = mainPage.navigateToSmartTvElectronicPage();
        TelephonesPage telephonesPage = smartTvElectronicPage.clickTelephonesButton();
        telephonesPage.clickSmartPhonesButton();
        telephonesPage.paginationBlock.openNextPage();
        telephonesPage.paginationBlock.openNextPage();
        telephonesPage.paginationBlock.openPreviousPage();

    }
}

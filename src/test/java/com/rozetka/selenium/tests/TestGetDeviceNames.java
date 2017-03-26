package com.rozetka.selenium.tests;

import com.rozetka.selenium.utils.SeleniumProperties;
import com.rozetka.selenium.utils.db.DbActions;
import com.rozetka.selenium.utils.objects.Device;
import com.rozetka.selenium.utils.pages.MainPage;
import com.rozetka.selenium.utils.pages.innerpages.smarttvelectronicpage.SmartTvElectronicPage;
import com.rozetka.selenium.utils.pages.innerpages.telephonespage.TelephonesPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.rozetka.selenium.utils.Email.sendEmailReport;

/**
 * Created by artem on 3/24/17.
 */
public class TestGetDeviceNames extends DbActions {

    private List<Device> topSoldDevices = new ArrayList<>();
    private String emailSendToReport = SeleniumProperties.getProperty("report.email");

    @Test
    public void testGetAndStoreToDbTopSoldDevices() throws SQLException {
        MainPage mainPage = MainPage.navigateTo(driver);
        SmartTvElectronicPage smartTvElectronicPage = mainPage.navigateToSmartTvElectronicPage();
        TelephonesPage telephonesPage = smartTvElectronicPage.clickTelephonesButton();
        telephonesPage.clickSmartPhonesButton();
        telephonesPage.sortItemsByPopularity();
        topSoldDevices.addAll(telephonesPage.getTopSoldDevices());
        telephonesPage.openNextPage();
        topSoldDevices.addAll(telephonesPage.getTopSoldDevices());
        telephonesPage.openNextPage();
        topSoldDevices.addAll(telephonesPage.getTopSoldDevices());
        saveDevicesToDb(topSoldDevices);
        sendEmailReport(emailSendToReport, generateReport());
    }

    @AfterMethod(alwaysRun = true)
    private void cleaningDB() throws SQLException {
        removeTestingDataFromDb();
    }
}

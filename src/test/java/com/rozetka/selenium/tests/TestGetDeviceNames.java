package com.rozetka.selenium.tests;

import com.rozetka.selenium.utils.BasicTestCase;
import com.rozetka.selenium.utils.objects.Device;
import com.rozetka.selenium.utils.pages.MainPage;
import com.rozetka.selenium.utils.pages.db.MySqlDb;
import com.rozetka.selenium.utils.pages.innerpages.smarttvelectronicpage.SmartTvElectronicPage;
import com.rozetka.selenium.utils.pages.innerpages.telephonespage.TelephonesPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 3/24/17.
 */
public class TestGetDeviceNames extends BasicTestCase {

    private List<Device> topSoldDevices = new ArrayList<>();
    private MySqlDb db;

    @BeforeClass
    private void establishDbConnection() {
        db = new MySqlDb();
    }

    @Test
    public void testGetAndStoreToDbTopSoldDevices() throws SQLException {
        MainPage mainPage = MainPage.navigateTo(driver);
        SmartTvElectronicPage smartTvElectronicPage = mainPage.navigateToSmartTvElectronicPage();
        TelephonesPage telephonesPage = smartTvElectronicPage.clickTelephonesButton();
        telephonesPage.clickSmartPhonesButton();
        telephonesPage.sortItemsByPopularity();

        topSoldDevices.addAll(telephonesPage.getTopSoldDevices());
        telephonesPage.paginationBlock.openNextPage();
        topSoldDevices.addAll(telephonesPage.getTopSoldDevices());
        telephonesPage.paginationBlock.openNextPage();
        topSoldDevices.addAll(telephonesPage.getTopSoldDevices());
        saveDevicesToDb(topSoldDevices);
    }

    @AfterClass(alwaysRun = true)
    private void closeDbConnection() {
        db.closeDbConnection();
    }

    private void saveDevicesToDb(List<Device> devices) throws SQLException {
        for (Device device: devices) {
            if (!checkIfDeviceAlreadyStored(device)) {
                db.executeQueryUpdate(String.format("Insert into TopSales(name, price) values ('%s', %s);",
                        device.getName(), device.getPrice()));
            }
        }
    }

    private boolean checkIfDeviceAlreadyStored(Device device) throws SQLException {
        return db.executeCustomQueryOneResult(
                String.format("Select count(*) from TopSales where name = '%s' and price = %s;",
                        device.getName(), device.getPrice())).equals("1");
    }
}

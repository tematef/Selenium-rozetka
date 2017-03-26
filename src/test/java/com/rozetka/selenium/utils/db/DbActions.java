package com.rozetka.selenium.utils.db;

import com.rozetka.selenium.utils.BasicTestCase;
import com.rozetka.selenium.utils.objects.Device;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 3/27/17.
 */
public abstract class DbActions extends BasicTestCase {

    protected void saveDevicesToDb(List<Device> devices) throws SQLException {
        for (Device device: devices) {
            if (!checkIfDeviceAlreadyStored(device)) {
                db.executeQueryUpdate(String.format("Insert into TopSales(name, price) values ('%s', %s);",
                        device.getName(), device.getPrice()));
            }
        }
    }

    protected String generateReport() throws SQLException {
        StringBuilder report = new StringBuilder();
        for (Device device: getTopSoldDevicesFromDb()) {
            report.append(device.getName()).append(" ").append(device.getPrice()).append("grn \n");
        }
        if (report.length() == 0) {
            return report.append("No devices were found").toString();
        }
        return report.toString();
    }

    protected void removeTestingDataFromDb() throws SQLException {
        db.executeQueryUpdate("delete from TopSales");
    }

    private List<Device> getTopSoldDevicesFromDb() throws SQLException {
        ResultSet set = db.executeQuery("Select * from TopSales;");
        List<Device> devices = new ArrayList<>();
        while (set.next()) {
            devices.add(new Device(set));
        }
        return devices;
    }

    private boolean checkIfDeviceAlreadyStored(Device device) throws SQLException {
        return db.executeCustomQueryOneResult(
                String.format("Select count(*) from TopSales where name = '%s' and price = %s;",
                        device.getName(), device.getPrice())).equals("1");
    }
}

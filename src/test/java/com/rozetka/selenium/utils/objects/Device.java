package com.rozetka.selenium.utils.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by artem on 3/24/17.
 */
public class Device {

    private String name;
    private int price;

    public Device(WebElement webElement) {
        this.name = webElement.
                findElement(By.xpath("../div[@class='g-i-tile-i-title clearfix']/a")).
                getText().
                trim();
        this.price = Integer.valueOf(webElement.
                findElement(By.xpath("../div[@class='inline']/div/div[@name='price']/div")).
                getText().
                replaceAll("[^0-9]", ""));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (price != device.price) return false;
        return name.equals(device.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price;
        return result;
    }
}

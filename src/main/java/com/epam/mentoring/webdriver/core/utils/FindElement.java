package com.epam.mentoring.webdriver.core.utils;

import com.epam.mentoring.webdriver.core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.NoSuchElementException;

/**
 * Created by Alyona_Chernova on 11/30/2015.
 */
public class FindElement {

    public static boolean isElementPresent(By locator) {
        try {
            DriverManager.getInstance().findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public static boolean isElementPresent(WebElement wElement) {
        try {
            wElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}

package com.epam.mentoring.webdriver.core.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Alyona_Chernova on 12/2/2015.
 */
public class FindIFrameForText {
    public static WebElement getMailBodyFrame(WebDriver driver) {
        return getFrame(driver, By.tagName("iframe"), "composeEditor");
    }

    public static WebElement getFrame(WebDriver driver, By by, String text) {
        WebElement frame = null;
        List<WebElement> ele = driver.findElements(by);
        for (WebElement el : ele) {
            if (el.getAttribute("id").contains(text)) {
                frame = el;
            }
        }
        return frame;
    }
}

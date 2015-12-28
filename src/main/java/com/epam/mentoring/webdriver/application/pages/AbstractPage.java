package com.epam.mentoring.webdriver.application.pages;

import com.epam.mentoring.webdriver.core.utils.FindElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Alyona_Chernova on 12/5/2015.
 */
public abstract class AbstractPage {
    public static final String COLOR_FOR_HIGHLIGHT = "yellow";

    abstract WebElement getIndicator();

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpen() {
        return FindElement.isElementPresent(getIndicator());
    }

    ;
}


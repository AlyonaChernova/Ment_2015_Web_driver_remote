package com.epam.mentoring.webdriver.core.utils;

/**
 * Created by Alyona_Chernova on 12/23/2015.
 */
public enum BrowserName {
    FIREFOX("firefox"), CHROME("chrome");

    private String description;

    BrowserName(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

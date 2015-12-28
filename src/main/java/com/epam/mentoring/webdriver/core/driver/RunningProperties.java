package com.epam.mentoring.webdriver.core.driver;

/**
 * Created by Alyona_Chernova on 12/21/2015.
 */
public class RunningProperties {
    public static String getBrowserName(){
        return System.getProperty("browser.name");
    }
}

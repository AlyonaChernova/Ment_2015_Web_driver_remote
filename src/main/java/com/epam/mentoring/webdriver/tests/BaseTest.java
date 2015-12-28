package com.epam.mentoring.webdriver.tests;

import com.epam.mentoring.webdriver.core.driver.DriverManager;
import com.epam.mentoring.webdriver.core.driver.RunningProperties;
import com.epam.mentoring.webdriver.core.log.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by Alyona_Chernova on 12/7/2015.
 */
public class BaseTest {

    @BeforeTest
    //@Parameters({"browserName"})
    public void openBrowser() {
        Logger.LOG.info("Current browser is: "+ RunningProperties.getBrowserName());
        DriverManager.open();
    }

    @AfterTest
    public void closeBrowser() {
        DriverManager.close();
        DriverManager.quit();
    }
}

package com.epam.mentoring.webdriver.core.driver;

import com.epam.mentoring.webdriver.core.log.Logger;
import com.epam.mentoring.webdriver.core.utils.BrowserName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.epam.mentoring.webdriver.core.log.Logger.LOG;

/**
 * Created by Alyona_Chernova on 11/27/2015.
 */
public class DriverManager {
    private static final String HUB_URL = "http://localhost:4444/wd/hub";
    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = getRemoteWebDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    private DriverManager() {
    }

    private static RemoteWebDriver getRemoteWebDriver() {
        RemoteWebDriver rwd = null;
        String currentBrowserName = RunningProperties.getBrowserName();
        try {
            switch (BrowserName.valueOf(currentBrowserName.toUpperCase())) {
                case FIREFOX:
                    rwd = new RemoteWebDriver(new URL(HUB_URL), DesiredCapabilities.firefox());
                    break;
                case CHROME:
                    rwd = new RemoteWebDriver(new URL(HUB_URL), DesiredCapabilities.chrome());
                    break;
                default:
                    rwd = new FirefoxDriver();
                    break;
            }
        } catch (MalformedURLException e) {
            LOG.error("Exception: " + e.getMessage());
            rwd = new FirefoxDriver();
        }
        return rwd;
    }

    public static void open() {
        getInstance().navigate().to(Environment.URL);
    }

    public static void close() {

        getInstance().close();
    }

    public static void quit() {
        getInstance().quit();
        driver = null;
    }
}

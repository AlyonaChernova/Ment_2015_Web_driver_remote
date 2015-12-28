package com.epam.mentoring.webdriver.core.runner;

import com.epam.mentoring.webdriver.core.driver.RunningPropertiesForRunner;
import com.epam.mentoring.webdriver.core.log.Logger;
import com.epam.mentoring.webdriver.core.utils.TestListener;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;


import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alyona_Chernova on 11/27/2015.
 */
public class TestRunner {

    public static void main(String[] args) {

        RunningPropertiesForRunner settings = new RunningPropertiesForRunner();
        CmdLineParser parser = new CmdLineParser(settings);
        try {
            parser.parseArgument(args);
            if (settings.isHelp()) {
                parser.printUsage(System.err);
                System.exit(0);
            }
            System.setProperty("browser.name", settings.getBrowserName());

            runTests();

        } catch (CmdLineException e) {
            Logger.LOG.error(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    private static void runTests() {
        TestListenerAdapter tla = new TestListener();
        TestNG testNG = new TestNG();
        testNG.addListener(tla);
        List<String> suites = new ArrayList<>();
        suites.add(".//testNG//testng.xml");
        testNG.setTestSuites(suites);
        testNG.run();
    }
}

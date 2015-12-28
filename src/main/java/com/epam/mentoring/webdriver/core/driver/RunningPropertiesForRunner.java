package com.epam.mentoring.webdriver.core.driver;

import com.epam.mentoring.webdriver.core.utils.BrowserName;
import org.kohsuke.args4j.Option;

/**
 * Created by Alyona_Chernova on 12/22/2015.
 */
public class RunningPropertiesForRunner {

    @Option(name = "-browser", usage = "Set browser for running tests", required = false)
    private BrowserName browserName;

    @Option(name = "-help", help = true)
    private boolean help;

    public String getBrowserName(){
        return browserName.toString();
    }

    public boolean isHelp() {
        return help;
    }
}

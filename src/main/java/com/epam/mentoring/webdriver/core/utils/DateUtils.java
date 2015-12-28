package com.epam.mentoring.webdriver.core.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Alyona_Chernova on 12/22/2015.
 */
public class DateUtils {
    public static final DateTimeFormatter DATE_WITH_TIME = DateTimeFormat.forPattern("dd.MM.yyyy_HH:mm:ss");
    public static final DateTimeFormatter DATE_WITH_TIME1 = DateTimeFormat.forPattern("dd-MM-yyyy(HH-mm-ss)");

    public static String getCurrentDate() {
        return getCurrentDate(DATE_WITH_TIME1);
    }

    public static String getCurrentDate(DateTimeFormatter formatter) {
        return new DateTime().toString(formatter);
    }
}

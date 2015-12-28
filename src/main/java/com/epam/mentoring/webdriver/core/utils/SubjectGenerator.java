package com.epam.mentoring.webdriver.core.utils;

import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * Created by Alyona_Chernova on 12/3/2015.
 */
public class SubjectGenerator {
    public static String getUniqueSubject() {
        DateTime dateTime = new DateTime();
        Timestamp timeStamp = new Timestamp(dateTime.getMillis());
        return "TestLetter_" + timeStamp.toString();
    }
}

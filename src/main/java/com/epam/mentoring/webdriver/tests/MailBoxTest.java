package com.epam.mentoring.webdriver.tests;

import com.epam.mentoring.webdriver.application.entity.Mail;
import com.epam.mentoring.webdriver.application.entity.MailBox;
import com.epam.mentoring.webdriver.application.pages.LoginPage;
import com.epam.mentoring.webdriver.application.pages.MailBoxPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Alyona_Chernova on 11/27/2015.
 */
public class MailBoxTest extends BaseTest {

    public MailBox mailBox;
    public MailBoxPage mailPage;
    public LoginPage loginPage;

    @Test(description = "1) Login to the mail box; 2) Assert, that the login is successful.")
    @Parameters({"login", "pass", "receiver", "body", "signature"})
    public void logIn(String login, String pass, String receiver, String body, String signature) {
        Mail email = new Mail(receiver, body, signature);
        mailBox = new MailBox(login, pass, email);
        loginPage = new LoginPage();
        mailPage = loginPage.logIn(mailBox);
        Assert.assertTrue(mailPage.isPageOpen(), "ERROR: Login wasn't successful");
    }


    @Test(dependsOnMethods = "logIn", description = "3) Create a new mail (fill addressee, subject and body fields).; 4) Save the mail as a draft.")
    public void writeMail() {
        Assert.assertTrue(mailPage.writeMail(mailBox.getEmail()), "ERROR: Email wasn't saved");
    }

    @Test(dependsOnMethods = "writeMail", description = "5) Verify, that the mail presents in Drafts. 6) Verify the draft content (addressee, subject and body – should be the same as in 3).")
    public void findInDrafts() {
        Assert.assertTrue(mailPage.verifyInDrafts(mailBox.getEmail()), "ERROR: Email wasn't found in drafts");
    }

    @Test(dependsOnMethods = "findInDrafts", description = "7) Send the mail.")
    public void sendMail() {
        Assert.assertTrue(mailPage.sendMail(), "ERROR: Email wasn't sent");
    }

    @Test(dependsOnMethods = "sendMail", description = "8) Verify, that the mail disappeared from Drafts.")
    public void verifyNotInDrafts() {
        Assert.assertFalse(mailPage.verifyInDrafts(mailBox.getEmail()), "ERROR: Email didn't disappear from drafts");
    }

    @Test(dependsOnMethods = "verifyNotInDrafts", description = "9) Verify, that the mail is in Sent.")
    public void findInOutcome() {
        Assert.assertTrue(mailPage.verifyInOutComing(mailBox.getEmail()), "ERROR: Email wasn't found in outcome");
    }

    @Test(dependsOnMethods = "findInOutcome", description = "Drag and drop to SpamBin")
    public void dragAndDropToSpam() {
        Assert.assertTrue(mailPage.dragAndDropToSpam(mailBox.getEmail()), "ERROR: Drag-and-droping to RecycleBin wasn't successful");
    }

    @Test(dependsOnMethods = "dragAndDropToSpam", description = "Delete from SpamBin")
    public void deleteMailFromSpam() {
        Assert.assertTrue(mailPage.moveFromSpam(mailBox.getEmail()), "ERROR: Deleting from SpamBin wasn't successful");
    }

    @Test(dependsOnMethods = "deleteMailFromSpam", description = "10) Log off.")
    public void logOut() {
        loginPage = mailPage.logOut();
        Assert.assertTrue(loginPage.isPageOpen(), "ERROR: Logout wasn't successful");
    }


}

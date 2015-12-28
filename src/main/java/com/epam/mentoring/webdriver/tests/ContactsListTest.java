package com.epam.mentoring.webdriver.tests;

import com.epam.mentoring.webdriver.application.entity.Mail;
import com.epam.mentoring.webdriver.application.entity.MailBox;
import com.epam.mentoring.webdriver.application.pages.ContactPage;
import com.epam.mentoring.webdriver.application.pages.LoginPage;
import com.epam.mentoring.webdriver.application.pages.MailBoxPage;
import com.epam.mentoring.webdriver.core.driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Alyona_Chernova on 12/14/2015.
 */
public class ContactsListTest extends BaseTest {
    public MailBox mailBox;
    public MailBoxPage mailPage;
    public LoginPage loginPage;
    public ContactPage contactPage;

    @Test(description = "Create new contact")
    @Parameters({"login", "pass", "receiver", "body", "signature"})
    public void logIn(String login, String pass, String receiver, String body, String signature) {
        Mail email = new Mail(receiver, body, signature);
        mailBox = new MailBox(login, pass, email);
        loginPage = new LoginPage();
        mailPage = loginPage.logIn(mailBox);
        Assert.assertTrue(mailPage.isPageOpen(), "ERROR: Login wasn't successful");
    }

    @Test(dependsOnMethods = "logIn", description = "Open page")
    public void openContactPage() {
        contactPage = new ContactPage(DriverManager.getInstance());
        contactPage.openPage();
        Assert.assertTrue(contactPage.isPageOpen(), "ERROR: Contact page wasn't open");
    }

    @Test(dependsOnMethods = "openContactPage", description = "Create new contact")
    public void createContact() {
        Assert.assertTrue(contactPage.createNewContact(mailBox.getEmail()), "ERROR: Contact wasn't created");
    }

    @Test(dependsOnMethods = "createContact", description = "Delete new contact")
    public void deleteContact() {
        contactPage.deleteNewContact();
        Assert.assertTrue(contactPage.isPageOpen(), "ERROR: Contact wasn't deleted");
    }
}

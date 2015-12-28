package com.epam.mentoring.webdriver.application.pages;

import com.epam.mentoring.webdriver.application.entity.MailBox;
import com.epam.mentoring.webdriver.core.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.mentoring.webdriver.core.log.Logger.LOG;

/**
 * Created by Alyona_Chernova on 12/5/2015.
 */
public class LoginPage extends AbstractPage {

    @FindBy(id = "mailbox__login")
    private WebElement inputLogin;

    @FindBy(id = "mailbox__password")
    private WebElement inputPassword;

    @FindBy(id = "mailbox__auth__button")
    private WebElement btLogin;

    @FindBy(id = "PH_authLink")
    private WebElement linkLogin;

    public LoginPage() {
        super(DriverManager.getInstance());
    }

    public MailBoxPage logIn(MailBox mailBox) {
        LOG.info("Login is starting up...");
        inputLogin.clear();
        inputLogin.sendKeys(mailBox.getLogin());
        inputPassword.clear();
        inputPassword.sendKeys(mailBox.getPass());
        btLogin.click();
        LOG.info("Login is done!");
        return new MailBoxPage();
    }

    @Override
    WebElement getIndicator() {
        return linkLogin;
    }

}

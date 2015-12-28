package com.epam.mentoring.webdriver.application.pages;

import com.epam.mentoring.webdriver.application.entity.Mail;
import com.epam.mentoring.webdriver.core.driver.DriverManager;
import com.epam.mentoring.webdriver.core.utils.FindIFrameForText;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.mentoring.webdriver.core.log.Logger.LOG;

/**
 * Created by Alyona_Chernova on 12/5/2015.
 */
public class MailBoxPage extends AbstractPage {
    public static final String SAVE_MESSAGE = "черновиках";
    public static final String SEND_CONFIRMATION_MESSAGE = "Ваше письмо отправлено. Перейти во Входящие";

    @FindBy(xpath = "//a[@id='PH_logoutLink']")
    private WebElement lnkLogout;

    @FindBy(css = "span.b-toolbar__btn__text.b-toolbar__btn__text_pad")
    private WebElement btNewMail;

    @FindBy(xpath = "//input[@name='Subject']")
    private WebElement inputNewMailSubject;

    @FindBy(css = "textarea.js-input.compose__labels__input")
    private WebElement inputNewMailReceiver;

    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement inputNewMailBody;

    @FindBy(xpath = "//span[text()='Сохранить']")
    private WebElement btSaveNewMail;

    @FindBy(className = "toolbar__message_info__link")
    private WebElement lnkSavedInDrafts;

    @FindBy(xpath = "//a[@class='b-nav__link js-shortcut' and @data-mnemo='drafts']")
    private WebElement lnkDrafts;

    @FindBy(id = "PH_user-email")
    private WebElement lnkUserEmail;

    @FindBy(xpath = "//span[text()='Отправить']")
    private WebElement btSave;

    @FindBy(xpath = "//div[@id='b-compose__sent']/div/div[1]/div")
    private WebElement lnkSaveSuccessMessage;

    @FindBy(xpath = "//a[@class='b-nav__link js-shortcut' and @href='/messages/sent/']")
    private WebElement lnkOutComing;

    @FindBy(xpath = "//a[@class='b-nav__link' and @href='/messages/spam/']")
    private WebElement lnkSpamBin;

    public MailBoxPage() {
        super(DriverManager.getInstance());
    }

    public boolean writeMail(Mail mail) {
        LOG.info("Writing new mail is starting up...");
        boolean isSuccessful = false;
        String saveMessage;
        btNewMail.click();

        fillMail(mail);

        btSaveNewMail.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOG.error("Exception: " + e.getMessage());
        }

        WebDriverWait wait = new WebDriverWait(DriverManager.getInstance(), 5);
        wait.until(ExpectedConditions.visibilityOf(lnkSavedInDrafts));

        saveMessage = lnkSavedInDrafts.getText();
        if (saveMessage.equals(SAVE_MESSAGE)) {
            isSuccessful = true;
        }
        LOG.info("Writing new email is done");
        return isSuccessful;
    }

    private void fillMail(Mail mail) {
        LOG.info("Fill in receiver");
        inputNewMailReceiver.clear();
        inputNewMailReceiver.sendKeys(mail.getReceiver());
        LOG.info("Fill in subject");
        inputNewMailSubject.clear();
        inputNewMailSubject.sendKeys(mail.getSubject());
        LOG.debug("Finding frame with email body");
        DriverManager.getInstance().switchTo().frame(FindIFrameForText.getMailBodyFrame(DriverManager.getInstance()));
        LOG.info("Fill in body");
        inputNewMailBody.sendKeys(mail.getBody());
        DriverManager.getInstance().switchTo().defaultContent();
    }

    public boolean verifyInDrafts(Mail mail) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOG.error("Exception: " + e.getMessage());
        }

        LOG.info("Verifying in drafts is starting up...");
        lnkDrafts.click();
        MailListPage mailListPage = new MailListPage();
        return mailListPage.checkMessageContent(mail, true);
    }

    public boolean sendMail() {
        boolean isSuccessful = false;
        LOG.info("Sending mail is starting up...");
        btSave.click();
        String textMessage = lnkSaveSuccessMessage.getText();
        if (textMessage.equals(SEND_CONFIRMATION_MESSAGE)) {
            isSuccessful = true;
        }
        LOG.info("Sending is done");
        return isSuccessful;
    }

    public boolean verifyInOutComing(Mail mail) {
        LOG.info("Verifying in outComing is starting up...");
        lnkOutComing.click();
        MailListPage mailListPage = new MailListPage();
        return mailListPage.checkMessageContent(mail, false);
    }

    public LoginPage logOut() {
        LOG.info("LogOut is starting up...");
        lnkLogout.click();
        LOG.info("LogOut is done");
        return new LoginPage();
    }


    public boolean dragAndDropToSpam(Mail mail) {
        LOG.info("Moving (by drag and drop) email to the Spam is starting up...");
        lnkOutComing.click();
        MailListPage mailListPage = new MailListPage();
        return mailListPage.moveToSpam(mail);
    }

    public boolean moveFromSpam(Mail mail) {
        LOG.info("Moving (by keyboard action) email from the Spam is starting up...");
        lnkSpamBin.click();
        MailListPage mailListPage = new MailListPage();
        return mailListPage.deleteFromSpam(mail);
    }

    @Override
    WebElement getIndicator() {
        return lnkUserEmail;
    }

}

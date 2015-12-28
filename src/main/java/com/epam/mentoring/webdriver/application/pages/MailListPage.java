package com.epam.mentoring.webdriver.application.pages;

import com.epam.mentoring.webdriver.application.entity.Mail;
import com.epam.mentoring.webdriver.core.driver.DriverManager;
import com.epam.mentoring.webdriver.core.utils.FindElement;
import com.epam.mentoring.webdriver.core.utils.FindIFrameForText;
import com.epam.mentoring.webdriver.core.utils.WebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.epam.mentoring.webdriver.core.log.Logger.LOG;

/**
 * Created by Alyona_Chernova on 12/7/2015.
 */
public class MailListPage extends AbstractPage {

    private static final String CLASSNAME_SUBJECT_AND_BODY = "b-datalist__item__subj";
    private static final String CLASSNAME_BODY = "b-datalist__item__subj__snippet";
    private static final String CLASSNAME_RECEIVER = "b-datalist__item__addr";

    @FindBy(className = "b-datalist__item__info")
    private List<WebElement> lstEmails;

    @FindBy(xpath = "//a[@class='js-href b-datalist__item__link']")
    private List<WebElement> lstSpam;

    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement inputBodyDraft;

    @FindBy(xpath = "//div[@class='js-helper js-readmsg-msg']")
    private WebElement inputBodySent;

    @FindBy(xpath = "//div[@title = 'Переключить вид']")
    private WebElement indicator;

    @FindBy(xpath = "//a[@class='b-nav__link' and @href='/messages/spam/']")
    private WebElement lnkSpamBin;

    @FindBy(className = "notify-message__title")
    private WebElement MoveMessage;

    public MailListPage() {
        super(DriverManager.getInstance());
    }

    public boolean checkMessageContent(Mail mail, boolean isDrafts) {
        boolean isFound = false;

        String lBody;
        WebElement mailLine = getMailRow(mail);

        if (mailLine != null) {
            mailLine.click();
            if (isDrafts) {
                DriverManager.getInstance().switchTo().frame(FindIFrameForText.getMailBodyFrame(DriverManager.getInstance()));
                lBody = inputBodyDraft.getText();
                DriverManager.getInstance().switchTo().defaultContent();
            } else {
                lBody = inputBodySent.getText();
            }
            if ((lBody.equals(mail.getBody() + mail.getSignature().replace("\\n", "\n")))) {
                isFound = true;
            } else {
                isFound = false;
            }
        }

        LOG.info("Verifying is done");
        return isFound;
    }

    private static WebElement getChild(WebElement parentElement, String className) {
        return parentElement.findElement(By.className(className));
    }

    public boolean moveToSpam(Mail mail) {
        WebElementUtils.dragAndDrop(getMailRow(mail), lnkSpamBin);
        return FindElement.isElementPresent(MoveMessage);
    }

    public boolean deleteFromSpam(Mail mail) {
        WebElement mailRow = null;
        for (WebElement we : lstSpam) {
            if (we.getAttribute("data-subject").equals(mail.getSubject())) {
                mailRow = we;
                break;
            }
        }
        if (mailRow != null) {
            WebElement checkBoxMailRow = getChild(mailRow, "b-checkbox__checkmark");
            WebElementUtils.clickByAction(checkBoxMailRow);
            WebElementUtils.sendKeyByAction(Keys.DELETE);
            LOG.debug("delete email is done");

            return FindElement.isElementPresent(MoveMessage);
        } else return false;

    }

    private WebElement getMailRow(Mail mail) {
        WebElement mailLine = null;
        for (WebElement we : lstEmails) {

            String lSubjectAndBody = getChild(we, CLASSNAME_SUBJECT_AND_BODY).getText();
            String lBody = getChild(we, CLASSNAME_BODY).getText();
            String lSubject = lSubjectAndBody.replace(lBody, "");
            String lReceiver = getChild(we, CLASSNAME_RECEIVER).getText();

            if ((lSubject.equals(mail.getSubject())))
                if ((lReceiver.equals(mail.getReceiver()))) {
                    mailLine = we;
                    break;
                }
        }

        return mailLine;
    }

    @Override
    WebElement getIndicator() {
        return indicator;
    }
}



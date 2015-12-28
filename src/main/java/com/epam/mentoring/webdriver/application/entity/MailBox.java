package com.epam.mentoring.webdriver.application.entity;

/**
 * Created by Alyona_Chernova on 12/3/2015.
 */
public class MailBox {
    private String login;
    private String pass;
    private Mail email;

    public MailBox(String login, String pass, Mail email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public Mail getEmail() {
        return email;
    }
}

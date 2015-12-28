package com.epam.mentoring.webdriver.application.entity;

import com.epam.mentoring.webdriver.core.utils.SubjectGenerator;

/**
 * Created by Alyona_Chernova on 11/27/2015.
 */
public class Mail {
        private String receiver;
        private String subject;
        private String body;
        private String signature;

        public String getReceiver() {
            return receiver;
        }

        public String getSubject() {
            return subject;
        }

        public String getBody() {
            return body;
        }

        public String getSignature() {
            return signature;
        }

    public Mail(String receiver, String body, String signature) {
        this.receiver = receiver;
        this.subject = SubjectGenerator.getUniqueSubject();
        this.body = body;
        this.signature = signature;

    }
}

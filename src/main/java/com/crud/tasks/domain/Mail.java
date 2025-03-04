package com.crud.tasks.domain;

import lombok.Getter;

@Getter
public final class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

    public static class MailBuilder {
        private String mailTo;
        private String subject;
        private String message;
        private String toCc;

        public MailBuilder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public MailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MailBuilder toCc(String toCc) {
            this.toCc = toCc;
            return this;
        }

        public Mail build() {
            return new Mail(mailTo, subject, message, toCc);
        }

    }

    public Mail(String mailTo, String subject, String message, String toCc) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
        this.toCc = toCc;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "mailTo='" + mailTo + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", toCc='" + toCc + '\'' +
                '}';
    }
}
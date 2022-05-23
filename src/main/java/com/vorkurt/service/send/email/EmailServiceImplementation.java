package com.vorkurt.service.send.email;

import lombok.NoArgsConstructor;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@NoArgsConstructor
public class EmailServiceImplementation {

    private EmailServiceImplementation(EmailServiceImplementationBuilder builder) {
    }

    public static class EmailServiceImplementationBuilder {
        private String from;
        private String to;
        private String host = "smtp.gmail.com";
        private Properties properties;
        private Session session;
        private String password;
        private MimeMessage message;
        private String title;
        private String body;

        public EmailServiceImplementationBuilder setFrom(String from) {
            this.from = from;
            return this;
        }

        public EmailServiceImplementationBuilder() {
        }

        public EmailServiceImplementationBuilder setTo(String to) {
            this.to = to;
            return this;
        }

        public EmailServiceImplementationBuilder setHost(String host) {
            this.host = host;
            return this;
        }

        public EmailServiceImplementationBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public String build() {
            EmailServiceImplementation email = new EmailServiceImplementation(this);
            properties = setProperties();
            session = new SessionService.SessionBuilderService(from, password, properties).build();
            return new MimeMessageService.TransformerMessageBuilder(session).from(to).to(from).body(body).title(
                    title).build();
        }

        private Properties setProperties() {
            Properties properties = System.getProperties();

            // Setup mail server
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            return properties;
        }

        public EmailServiceImplementationBuilder title(String titleMessage) {
            this.title = titleMessage;
            return this;
        }

        public EmailServiceImplementationBuilder body(String message) {
            this.body = message;
            return this;
        }

    }
}

package com.vorkurt.service.send.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MimeMessageService {
    private final MimeMessage message;

    private MimeMessageService(TransformerMessageBuilder transformerMessageBuilder){
        this.message = transformerMessageBuilder.message;
    }

    public static class TransformerMessageBuilder{
        private final Session session;
        private MimeMessage message;
        private String from;
        private String to;
        private String title;
        private String body;

        public TransformerMessageBuilder(Session session) {
            this.session = session;
        }

        public String build() {
            try {
                message = new MimeMessage(session);
                // Create a default MimeMessage object.

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject(title);

                // Now set the actual message
                message.setText(body);

                System.out.println("sending...");
                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
                return "Sent message successfully....";
            } catch (MessagingException mex) {
                mex.printStackTrace();
                return "Error";
            }
        }

        public TransformerMessageBuilder from(String fromMessage){
            this.from = fromMessage;
            return this;
        }

        public TransformerMessageBuilder to(String toMessage){
            this.to = toMessage;
            return this;
        }

        public TransformerMessageBuilder body(String message){
            this.body = message;
            return this;
        }

        public TransformerMessageBuilder title(String title){
            this.title = title;
            return this;
        }

    }
}

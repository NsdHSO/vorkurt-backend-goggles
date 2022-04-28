package com.vorkurt.service.send.email;

import org.springframework.beans.factory.annotation.Value;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionService {
    private final Session session;

    private SessionService(SessionBuilderService service){
        this.session = service.session;
    }

    public Session getSession() {
        return session;
    }

    public static  class SessionBuilderService {
        private final Session session;

        public SessionBuilderService(String email,
                                     @Value("${spring.mail.password}")String password, Properties properties) {
            session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(email, password);

                }

            });
        }

        public Session build(){
            return session;
        }
    }

}

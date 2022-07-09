package com.vorkurt.controller.auth;

import com.vorkurt.entity.auth.Auth;
import com.vorkurt.service.send.email.EmailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.username}")
    private String sendFrom;
    @PostMapping
    public Auth sendEmail(@RequestBody Auth auth){
        new EmailServiceImplementation.EmailServiceImplementationBuilder()
                .body(auth.getMessage())
                .title(auth.getTitle())
                .setTo(auth.getEmail())
                .setFrom(sendFrom)
                .setPassword(password)
                .build() ;
        return new Auth();
    }
}

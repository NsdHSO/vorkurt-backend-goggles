package com.vorkurt.controller.auth;

import com.vorkurt.entity.auth.Auth;
import com.vorkurt.service.send.email.EmailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping
    public Auth sendEmail(@RequestBody Auth auth){
        new EmailServiceImplementation.EmailServiceImplementationBuilder()
                .body(auth.getMessage())
                .title(auth.getTitle())
                .setTo(auth.getEmail())
                .build() ;
        return new Auth();
    }
}

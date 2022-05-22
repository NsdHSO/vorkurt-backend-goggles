package com;

import com.vorkurt.service.send.email.EmailServiceImplementation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@ComponentScan({"com.vorkurt.controller", "com.vorkurt.service", "com.core"})
@EntityScan({"com.vorkurt.entity",})
@EnableJpaRepositories({"com.vorkurt.repository"})
@EnableScheduling
public class VorkurtMidtierApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(VorkurtMidtierApplication.class)
                .run(args);


    }

}


package com.vorkurt.service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailScheduler {
    @Scheduled(cron="0 */3 * ? * *")
    public void sendEmail(){
        System.out.println("Cron "+ new Date());
    }
}

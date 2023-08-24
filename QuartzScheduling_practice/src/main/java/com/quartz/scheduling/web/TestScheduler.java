package com.quartz.scheduling.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestScheduler {
    private static final Logger logger = LoggerFactory.getLogger(TestScheduler.class);
    //
    @Scheduled(cron= "0/10 * * * * ?")
    public void run() {
        logger.info("[MYTEST] test batch {}", LocalDateTime.now());
    }
}

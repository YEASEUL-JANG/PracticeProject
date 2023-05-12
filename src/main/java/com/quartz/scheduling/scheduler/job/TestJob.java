package com.quartz.scheduling.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

public class TestJob extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(TestJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("[QUARTZ_TEST] test batch {}", LocalDateTime.now());
    }
}

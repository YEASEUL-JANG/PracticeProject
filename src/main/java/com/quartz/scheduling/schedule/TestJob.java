package com.quartz.scheduling.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@PersistJobDataAfterExecution //작업실행 후 해당 작업의 jobdatamap을 유지
@DisallowConcurrentExecution //동시 작업실행 방지
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("[TestJob] Job Executed");

        String jobName = context.getJobDetail().getKey().getName();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
//        int idx = Integer.parseInt((String)dataMap.get("Idx"));
        JobKey jobkey = context.getJobDetail().getKey();
        //LocalDateTime dateTime = LocalDateTime.parse((String)dataMap.get("dateTime"),
          //      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        log.info("[TestJob] jobkey: {}, dateTime: {}",jobkey);

//        Map map = new HashMap<>();
//        map.put("jobName", jobName);
//        map.put("idx",idx);
//        map.put("dateTime",dateTime);


    }
}

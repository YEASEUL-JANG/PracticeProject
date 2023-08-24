package com.quartz.scheduling.config.quartz;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;
@Configuration
@RequiredArgsConstructor
public class JobConfig {
//
//    //쿼트 스케줄 객체
//    private final Scheduler scheduler;
//
//    @PostConstruct//클래스가 인스턴스화 되자마자 자동으로 동작
//    public void run() {
//        JobDetail detail = runJobDetail(TestJob.class, new HashMap<>());
//
//        try{//크론형식 지정 후 스케줄 시작
//            scheduler.scheduleJob(detail, runJobTrigger("0/10 * * * * ?"));
//        }catch (SchedulerException e){
//            e.printStackTrace();
//        }
//    }
//    public Trigger runJobTrigger(String scheduleExp){
//        //크론 스케줄 사용
//        return TriggerBuilder.newTrigger()
//                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
//    }
//    public JobDetail runJobDetail(Class job, Map params){
//        JobDataMap jobDataMap = new JobDataMap();
//        jobDataMap.putAll(params);
//        //스케줄 생성
//        return newJob(job).usingJobData(jobDataMap).build();
//    }
}

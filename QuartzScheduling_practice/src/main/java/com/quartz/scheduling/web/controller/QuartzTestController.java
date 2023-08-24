package com.quartz.scheduling.web.controller;

import com.quartz.scheduling.schedule.QuartzHandler;
import com.quartz.scheduling.schedule.TestJob;
import com.quartz.scheduling.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class QuartzTestController {

    private final QuartzHandler quartzHandler;

    @GetMapping("/add")
    public ApiResponse add(@RequestParam(value="name")String name) throws SchedulerException {
        Map map = new HashMap<>();
        map.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        quartzHandler.addJob(TestJob.class,name,"test입니다",map,"0/10 * * * * ?");
        return ApiResponse.success();
    }
    @GetMapping("/dlt")
    public ApiResponse delete(@RequestParam(value = "name") String name) throws SchedulerException {

        quartzHandler.deleteJob(name);

        return ApiResponse.success();
    }
}

package com.example.secondservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/second-service")
public class SecondServiceController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Second Service !!";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-header") String header){
        log.info(header);
        return "Hello world in Second Service";
    }
}

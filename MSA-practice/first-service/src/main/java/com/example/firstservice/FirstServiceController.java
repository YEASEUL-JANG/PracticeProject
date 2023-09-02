package com.example.firstservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@Log4j2
public class FirstServiceController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First Service !!";
    }
    @GetMapping("/message")
    public String message(@RequestHeader("first-header") String header){
        log.info(header);
        return "Hello world in First Service";
    }
}

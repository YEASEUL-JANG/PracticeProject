package com.example.firstservice;

import com.netflix.discovery.converters.Auto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Log4j2
public class FirstServiceController {
    Environment env;

    @Autowired
    //생성자 주입방식 (Bean으로 등록되어 스프링에서 해당 빈이 생성될 때 environment 객체를 같이 주입한다.)
    public FirstServiceController(Environment env){
        this.env= env;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First Service !!";
    }
    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){
        log.info(header);
        return "Hello world in First Service";
    }
    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port={}",request.getServerPort());
        return "Hi, there. This is a message from First Service";
    }
}

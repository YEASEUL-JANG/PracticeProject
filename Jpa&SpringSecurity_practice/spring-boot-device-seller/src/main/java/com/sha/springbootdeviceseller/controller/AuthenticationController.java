package com.sha.springbootdeviceseller.controller;

import com.sha.springbootdeviceseller.model.User;
import com.sha.springbootdeviceseller.service.AuthenticationService;
import com.sha.springbootdeviceseller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;
    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user){
            //아이디 중복확인 검사
        if(userService.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //유저 테이블에 저장
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    // 로그인요청
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){
        return new ResponseEntity<>(
                authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }//로그인 유저 객체에 토큰과 함께 정보 저장하여 반환
}

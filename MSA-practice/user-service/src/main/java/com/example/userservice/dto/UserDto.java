package com.example.userservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private Date createdAt;

    private String encryptedPwd;

    private List<ResponseUser.ResponseOrder> orders;
}

package com.example.userservice.service;

import com.example.userservice.vo.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto createUser(UserDto userDto);
}

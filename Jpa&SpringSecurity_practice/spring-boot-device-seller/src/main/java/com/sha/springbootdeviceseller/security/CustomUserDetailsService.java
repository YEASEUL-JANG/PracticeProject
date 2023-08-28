package com.sha.springbootdeviceseller.security;

import com.sha.springbootdeviceseller.model.User;
import com.sha.springbootdeviceseller.service.UserService;
import com.sha.springbootdeviceseller.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //이름으로 유저를 찾아 유저객체에 대입.
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        //권한을 불러와서 변수에 저장.
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

        //UserDetails 객체로 반환한다.
        return UserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .username(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}

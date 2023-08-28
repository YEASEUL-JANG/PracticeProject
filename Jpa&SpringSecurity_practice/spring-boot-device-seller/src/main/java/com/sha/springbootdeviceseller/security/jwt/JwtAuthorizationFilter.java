package com.sha.springbootdeviceseller.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        //http요청에 따른 인증 객체를 만들어 온다.
        Authentication authentication = jwtProvider.getAuthentication(httpServletRequest);
        //토큰이 널이 아니고 타당하다
        if(authentication != null && jwtProvider.isTokenValid(httpServletRequest)){

            //security의 contextholder 영역에 인증토큰 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}

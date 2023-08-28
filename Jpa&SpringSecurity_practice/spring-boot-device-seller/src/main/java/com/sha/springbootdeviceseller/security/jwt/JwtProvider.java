package com.sha.springbootdeviceseller.security.jwt;

import com.sha.springbootdeviceseller.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);

    //요청 request에서 인증토큰이 있으면 사용자 정보를 userDetails객체에 담아 반환한다.
    Authentication getAuthentication(HttpServletRequest request);

    //토큰 유효기간 검증
    boolean isTokenValid(HttpServletRequest request);
}

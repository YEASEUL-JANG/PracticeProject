package com.sha.springbootdeviceseller.security.jwt;

import com.sha.springbootdeviceseller.security.UserPrincipal;
import com.sha.springbootdeviceseller.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProviderImpl implements JwtProvider {
    @Value("${app.jwt.secret}")
    private String JWT_SERCRET;

    @Value("${app.jwt.expiration-in.ms}")
    private Long JWT_EXPIRATION_IN_MS;


    @Override
    public String generateToken(UserPrincipal auth){
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Key key = Keys.hmacShaKeyFor(JWT_SERCRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles",authorities)
                .claim("userId",auth.getId())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_IN_MS))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request){
        //요청 request 헤더를 확인해서 인증토큰이 있으면 사용자 정보를 userDetails객체에 담아 반환한다.
        Claims claims = extractClaims(request);
        if(claims == null){
            return null;
        }
        String username = claims.getSubject();
        Long userId = claims.get("userId",Long.class);
        //권한 불러와서 ROLE_형식으로 담
        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority) //ROLE_형식으로 바꿈
                .collect(Collectors.toSet());//set에 다시 담음
        //UserDetails 객체로 재생성.
        UserDetails userDetails = UserPrincipal.builder()
                .username(username)
                .authorities(authorities)
                .id(userId)
                .build();
        if(username == null){
            return null;
        }//UsernamePasswordAuthenticationToken의 조상이 authentication 객체
        return new UsernamePasswordAuthenticationToken(userDetails,null,authorities);
    }
    //토큰 유효기간 검증
    @Override
    public boolean isTokenValid(HttpServletRequest request){
        Claims claims = extractClaims(request);
        if(claims==null){
            return false;
        }
        if(claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }


    //요청 request의 header 에 담긴 인증토큰을 확인하여 있다면 사용자 정보가 담긴 claims 객체를 반환하여 넘긴다.
    private Claims extractClaims(HttpServletRequest request){
        String token = SecurityUtils.extractAuthTokenFromRequest(request);
        if(token == null){
            return null;
        }
        Key key = Keys.hmacShaKeyFor(JWT_SERCRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

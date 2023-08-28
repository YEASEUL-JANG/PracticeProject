package com.sha.springbootdeviceseller.security;

import com.sha.springbootdeviceseller.model.Role;
import com.sha.springbootdeviceseller.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration은 @Component를 포함하는데, 외부 라이브러리 또는 내장 클래스를 bean으로 등록하고자할 때, 1개이상의 @Bean을 제공하는 클래스에 붙여 사용한다.
//@Component는 개발자가 직접 작성한 클래스를 bean으로 등록하고자할 때 사용한다.
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http.cors();
        http.csrf().disable(); //cross-site-request-forgery (세션방식을 사용하지 않음 -> 즉 서버에 인증정보를 저장하지 않기때문에
        // csrf 설정이 불필요함.  )
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll()//로그인과 회원가입 모두접근가능
                .antMatchers(HttpMethod.GET,"/api/device").permitAll()//기기 전체 리스트보기는 모두 접근가능
                .antMatchers("/api/device/**").hasRole(Role.ADMIN.name())//나머지는 admin만 접근가능
               .anyRequest().authenticated();//나머지는 인증이 있어야만 접속가능.
        //UsernamePasswordAuthenticationfilter가 작동하기 전에 jwt필터를 적용시킨다.
        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    //jwtfilter는 오직 security config아래에서만 사용되기 때문에 component로 지정할 필요는 없다.
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter();
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Cross-Origin Resource Sharing,CORS 설정 : 특 다른 출처의 자원을 공유할 수 있게 설정하는 권한체제
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")//요청을 허용하는 클라이언트의 주소
                        .allowedMethods("*");//허용할 http 메소드 (post, get)
            }
        };
    }
}

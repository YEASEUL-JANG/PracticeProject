package com.sha.springbootdeviceseller.security;

import com.sha.springbootdeviceseller.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    //transient : 직렬화(바이트코드화)를 막음. 바깥에 노출되지 않는 일시적으로만 사용되는 변수이며 본질적으로 메모리 안에서만 사용되어야 한다.
    //@transient : 엔티티에서 db에 저장되지 않고 메모리에서만 사용되는 필드나 속성을 말한다.
    transient private  String password;
    transient private User user;//로그인시에만 사용할것. JWT에서 사용하지 않는다.
    private Set<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

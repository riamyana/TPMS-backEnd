package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class Login implements UserDetails {
//    private int id;
//    private String userName;
//    private String password;
//    private String email;
//    private String role;

    CustomLogin customLogin;

    public Login(CustomLogin customLogin) {
//        this.userName = userName;
//        this.password = password;
//        this.email = email;
//        this.role = role;

        this.customLogin = customLogin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority =
//                new SimpleGrantedAuthority(role.name());
//        return Collections.singletonList(authority);

        return Collections.singleton(new SimpleGrantedAuthority(customLogin.getRole()));
    }

    @Override
    public String getPassword() {
        return customLogin.getPassword();
    }

    @Override
    public String getUsername() {
        return customLogin.getUserName();
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

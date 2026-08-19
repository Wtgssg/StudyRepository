package com.success.oracle.Security;

import com.success.oracle.Enum.Role;
import com.success.oracle.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UserDetailsImpl  {//implements UserDetails {
//    private User user;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Role role = user.getRole();
//
//       //把字符串包装成 Spring Security 认识的“权限对象”
//        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}

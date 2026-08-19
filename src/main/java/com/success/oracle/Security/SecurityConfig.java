package com.success.oracle.Security;

import com.success.oracle.entity.User;
import com.success.oracle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/Login/login").permitAll()
//                .antMatchers("/user/all").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll();
//
//        return http.build();
//    }
}



//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("admin")
//                .password("{noop}123456")  // {noop} 表示明文密码，仅用于学习
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        // 创建基于内存的用户信息管理器
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        manager.createUser(
//                // 创建UserDetails对象，用于管理用户名、用户密码、用户角色、用户权限等内容
//                User.withDefaultPasswordEncoder().username("user").password("user123").roles("USER").build()
//        );
//        // 如果自己配置的有账号密码, 那么上面讲的 user 和 随机字符串 的默认密码就不能用了
//        return manager;
//    }





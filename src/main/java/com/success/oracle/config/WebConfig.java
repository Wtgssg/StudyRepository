package com.success.oracle.config;

import com.success.oracle.interceptor.LoginIntercept;
import com.success.oracle.interceptor.LoginInterceptor;
import com.success.oracle.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginIntercept())
//                .addPathPatterns("/**") ;     // 拦截哪些路 // 排除哪些路径

        registry.addInterceptor(new PermissionInterceptor())
                .addPathPatterns("/**");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/callback", "/error");
    }
}
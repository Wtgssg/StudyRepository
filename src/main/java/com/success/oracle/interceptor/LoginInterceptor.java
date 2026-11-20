package com.success.oracle.interceptor;

import com.success.oracle.annotation.RequiresPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Slf4j
@Component
public class LoginInterceptor  implements HandlerInterceptor {
    private static final String SSO_LOGIN_URL = "http://localhost:8081/login";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequiresPermission annotation = handlerMethod.getMethodAnnotation(RequiresPermission.class);
        if (annotation == null) {
            annotation = handlerMethod.getBeanType().getAnnotation(RequiresPermission.class);
        }

        if (annotation == null) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            // 认证通过，把用户信息透传
            request.setAttribute("userId", session.getAttribute("userId"));
            request.setAttribute("username", session.getAttribute("username"));
            return true;
        }

        String returnUrl = buildReturnUrl(request);
        String redirectUrl = SSO_LOGIN_URL + "?returnUrl="
                + URLEncoder.encode(returnUrl, "UTF-8");

        log.warn("未登录访问拦截, URI: {}, 重定向到: {}", request.getRequestURI(), redirectUrl);
        response.sendRedirect(redirectUrl);
        return false;

    }
    private String buildReturnUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String query = request.getQueryString();
        return (query == null) ? url : url + "?" + query;
    }
}

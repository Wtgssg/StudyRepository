package com.success.oracle.interceptor;

import com.success.oracle.annotation.RequiresPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        // 1. 只拦截 Controller 方法
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//       //2. 检查类有没有目标注解
//        // 2. 检查方法上是否有 @RequireLogin 注解
//        RequiresPermission annotation = handlerMethod.getMethodAnnotation(RequiresPermission.class);
//        if (annotation == null) {
//            // 方法上没有，检查类上有没有
//            annotation = handlerMethod.getBeanType().getAnnotation(RequiresPermission.class);
//        }
//
//        // 3. 没有 @RequireLogin 注解，直接放行
//        if (annotation == null) {
//            return true;
//        }
//
//        // 4. 有 @RequiresPermission 注解，检查 Session
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("userId") == null) {
//            log.warn("未登录访问拦截，URI: {}", request.getRequestURI());
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("{\"code\":401,\"message\":\"请先登录\"}");
//            return false;
//        }
//
//        // 5. 认证通过，把用户信息存入 request，供 Controller 使用
//        request.setAttribute("userId", session.getAttribute("userId"));
//        request.setAttribute("username", session.getAttribute("username"));
//
        return true;


    }
}

package com.success.oracle.Aspect;

import com.success.oracle.annotation.CurrentUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@Aspect
@Component
public class AuthAspect {

    @Around("execution(* com.success.oracle.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 获取 Session
        //ServletRequestAttributes：此类主要存储HttpRequest和HttpResponse两个属性
        //RequestContextHolder：在任何一个被 Spring 管理的 Bean 中（哪怕没有 Controller 参数），都能拿到当前请求的 HttpServletRequest 和 HttpSession。
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //若不存在则返回null


        ((MethodSignature)joinPoint.getSignature()).getMethod();

        HttpSession session = attributes.getRequest().getSession(false);
        Long userId = session != null ? (Long) session.getAttribute("userId") : null;
        String username = session != null ? (String) session.getAttribute("username") : null;
        // 2. 获取方法信息

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();

        // 3. 处理 @CurrentUser

        for (int i = 0; i < method.getParameterAnnotations().length; i++) {
            for (Annotation anno : method.getParameterAnnotations()[i]) {
                if (anno instanceof CurrentUser) {
                    args[i] = userId;  // 替换参数值
                }
            }
        }
        //继续执行被拦截的方法
        return joinPoint.proceed(args);

    }
}

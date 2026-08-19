package com.success.oracle.Aspect;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
@Slf4j
public class LogAspect {
//        @Pointcut("execution(* com.success.oracle.controller.*.*(..))")
//        public void test(){};
//
//
//    @Pointcut("execution(* com.success.oracle.controller.UserController.getUserNameById(long)) "+
//              " && args(id)  ")
//    public void Login(long id){};

    @Pointcut("@annotation(com.success.oracle.annotation.Logs)")
    public void LogMethod(){}

    @Around("LogMethod()")
    public Object LogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("===============");
        log.info("有log注解标注的方法是"+ method.getName());
        log.info("===============");


        return joinPoint.proceed();

    }

    @Around("execution(* com.success.oracle.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        HttpSession session = request.getSession(false);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();

        Long userId = session != null ? (Long) session.getAttribute("userId") : null;
        String username = session != null ? (String) session.getAttribute("username") : "未登录";

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String fullUrl = uri + (queryString != null ? "?" + queryString : "");
        String ip = request.getRemoteAddr();

        long startTime = System.currentTimeMillis();

        // =========================================================
        // ============ 【前置】打印请求日志 ============
        // =========================================================
        log.info("┌─────────────────────────────────────────────────────────────");
        log.info("│ 【请求】{} {}", request.getMethod(), fullUrl);
        log.info("│ 【时间】{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        log.info("│ 【用户】{} ({})", username, userId);
        log.info("│ 【IP】{}", ip);
        log.info("│ 【类】{}.{}", className, methodName);
        //log.info("│ 【参数】{}", args != null ? JSON.toJSONString(args) : "无");
        log.info("└─────────────────────────────────────────────────────────────");

        // =========================================================
        // ============ 【执行】目标方法 ============
        // =========================================================
        Object result;
        try {
            result = joinPoint.proceed(args);

            long cost = System.currentTimeMillis() - startTime;

            // =========================================================
            // ============ 【后置】打印响应日志 ============
            // =========================================================
            log.info("┌─────────────────────────────────────────────────────────────");
            log.info("│ 【响应】耗时: {}ms", cost);
            log.info("│ 【返回】{}", result != null ? JSON.toJSONString(result) : "void");
            if (cost > 3000) {
                log.warn("│ 【慢请求】耗时超过3秒！");
            }
            log.info("└─────────────────────────────────────────────────────────────");

            return result;
        } catch (Exception e) {
            long cost = System.currentTimeMillis() - startTime;

            // =========================================================
            // ============ 【异常】打印异常日志 ============
            // =========================================================
            log.error("┌─────────────────────────────────────────────────────────────");
            log.error("│ 【异常】耗时: {}ms", cost);
            log.error("│ 【类型】{}", e.getClass().getSimpleName());
            log.error("│ 【消息】{}", e.getMessage());
            log.error("└─────────────────────────────────────────────────────────────");

            throw e;  // 抛出给全局异常处理器
        }
    }
}
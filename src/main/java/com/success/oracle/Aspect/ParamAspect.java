package com.success.oracle.Aspect;

import com.success.oracle.annotation.ValidId;
import com.success.oracle.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.sound.midi.MetaMessage;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class ParamAspect {
    @Around("execution(* com.success.oracle.controller.*.*(..))")
    public Object validateId(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();

        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                String[] paramNames = methodSignature.getParameterNames();
                if(annotations[i][j] instanceof ValidId){
                   //空值校验
                    if (args[i] == null || (args[i] instanceof String && ((String) args[i]).trim().isEmpty())){
                        throw new CustomException("999",paramNames[i] + "不能为空");
                    }

                    log.info("参数校验通过: {}.{}= {}", method.getName(),paramNames[i], args[i]);
                }

            }

        }
        return joinPoint.proceed();
    }
}

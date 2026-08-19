package com.success.oracle.interceptor;

import com.success.oracle.annotation.RequiresPermission;
import com.success.oracle.entity.User;
import com.success.oracle.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 判断是否是 Controller 方法（避免静态资源等非 HandlerMethod 报错）
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 2. 获取 Controller 类名和方法名
        String className = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();

        log.info("===== 拦截到请求: {}.{} =====", className, methodName);

        // 3. 遍历方法的所有参数
        MethodParameter[] parameters = handlerMethod.getMethodParameters();

        for (MethodParameter parameter : parameters) {
            // 获取参数上的 @RequestParam 注解
            RequestParam requestParam = parameter.getParameterAnnotation(RequestParam.class);

            if (requestParam != null) {
                // 获取参数名（Spring 通过字节码获取，需要编译时保留参数名）
                String paramName = parameter.getParameterName();
                // 如果 paramName 为 null，使用注解中的 value 作为备选
                if (paramName == null && requestParam != null) {
                    paramName = requestParam.value();
                    if (paramName.isEmpty()) {
                        paramName = requestParam.name();
                    }
                }
                // 获取参数类型（简单类名）
                String paramType = parameter.getParameterType().getSimpleName();

                // 获取注解属性
                boolean required = requestParam.required();


                log.info("@RequestParam 参数: {} (类型: {}), required={}",
                        paramName, paramType, required);
            }
        }

        log.info("===== 权限校验通过，放行 =====\n");
        return true;
    }


}
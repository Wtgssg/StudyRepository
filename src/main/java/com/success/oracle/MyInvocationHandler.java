package com.success.oracle;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("动态代理前置日志");
        Object result = method.invoke(target,objects);
        System.out.println("动态代理后置日志");
        return null;
    }
}

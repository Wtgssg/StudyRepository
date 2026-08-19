package com.success.oracle.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})  // 可以用在类或方法上
@Retention(RetentionPolicy.RUNTIME)              // 运行时保留，供反射读取
@Documented
public @interface RequiresPermission {


}
package com.success.oracle.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})  // 可以用在类或方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface Logs {
}

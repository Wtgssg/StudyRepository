package com.success.oracle.util;

import com.success.oracle.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice()
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public Result<Void> handleCustomException(CustomException e) {
        log.warn("业务异常：{}", e.getOutMsg());
        return Result.error(e.getOutCode(), e.getOutMsg());
    }
}
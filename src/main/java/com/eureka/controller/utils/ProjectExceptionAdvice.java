package com.eureka.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Eureka
 * @create 2022/9/1 14:40
 */
// 作为SpringMVC的异常处理器，可以捕获异常并返回给前端
@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public R handleException(Exception e) {
        e.printStackTrace();
        return new R("服务器异常");
    }
}

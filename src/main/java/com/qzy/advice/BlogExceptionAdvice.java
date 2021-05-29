package com.qzy.advice;


import com.qzy.exception.BlogException;
import com.qzy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BlogExceptionAdvice {

    @ExceptionHandler(BlogException.class)
    public Result<?> exceptionHandle(BlogException blogException) {
        log.error("统一异常处理", blogException);
        return new Result<>(blogException.getErrorCode(), blogException.getMessage());
    }
}

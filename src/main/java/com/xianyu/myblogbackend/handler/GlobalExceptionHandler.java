package com.xianyu.myblogbackend.handler;


import com.xianyu.myblogbackend.model.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    //当服务层抛出异常时，会自动进行捕获并且返回
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){

        log.info("异常信息:{}",e.getMessage());

        return Result.error(e.getMessage());
    }

}

package com.development.Monopoly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnExpectedErrorAdvice {
    @ResponseBody
    @ExceptionHandler(UnExpectedErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String UnExpectedErrorHandler(UnExpectedErrorException exception){
        return exception.getMessage();
    }
}

package com.development.Monopoly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SquareNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SquareNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String SquareNotFoundHandler(SquareNotFoundException exception){
        return exception.getMessage();
    }
}

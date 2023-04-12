package com.development.Monopoly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DuplicateColorAdvice {
    
    @ResponseBody
    @ExceptionHandler(DuplicateColorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String DuplicateColorHandler(DuplicateColorException ex){
        return ex.getMessage();
    }
}

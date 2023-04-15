package com.development.Monopoly.exception;

public class UnExpectedErrorException extends RuntimeException{
    public UnExpectedErrorException(int code){
        super("Lỗi không xác định " + code);
    }
}

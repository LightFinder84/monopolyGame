package com.development.Monopoly.exception;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(){
        super("Sai mật khẩu");
    }
}

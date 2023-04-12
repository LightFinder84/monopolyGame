package com.development.Monopoly.exception;

public class DuplicateNameException extends RuntimeException{
    public DuplicateNameException(){
        super("Tên này đã được sử dụng!");
    }
}

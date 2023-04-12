package com.development.Monopoly.exception;

public class DuplicateColorException extends RuntimeException{
    public DuplicateColorException(){
        super("Màu này đã được sử dụng!");
    }
}

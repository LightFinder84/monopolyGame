package com.development.Monopoly.exception;

public class SquareNotFoundException extends RuntimeException{
    public SquareNotFoundException(Long id){
        super("Không tìm thấy bàn chơi số " + id);
    }
}

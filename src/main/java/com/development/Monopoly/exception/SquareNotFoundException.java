package com.development.Monopoly.exception;

public class SquareNotFoundException extends RuntimeException{
    public SquareNotFoundException(int id){
        super("Không tìm thấy bàn chơi số " + id);
    }
}

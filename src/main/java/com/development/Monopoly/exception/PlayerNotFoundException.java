package com.development.Monopoly.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(){
        super("Người chơi không tồn tại.");
    }
}

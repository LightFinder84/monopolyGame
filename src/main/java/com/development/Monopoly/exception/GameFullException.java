package com.development.Monopoly.exception;

public class GameFullException extends RuntimeException {
    public GameFullException(){
        super("Phòng chơi đã đầy");
    }
}

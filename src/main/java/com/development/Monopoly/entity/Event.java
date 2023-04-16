package com.development.Monopoly.entity;

public class Event {
    private String message;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // constructor 
    Event(String message){
        this.message = message;
    }
}

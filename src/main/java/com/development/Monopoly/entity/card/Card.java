package com.development.Monopoly.entity.card;

public abstract class Card {
    private int id;
    private String name;
    private String messageString;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMessageString() {
        return messageString;
    }
    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Card(int id, String name, String messageString){
        this.id = id;
        this.messageString = messageString;
    }
}

package com.development.Monopoly.entity;

import com.development.Monopoly.Utils.PlayerStatus;

public class Player {

    private int id;
    private String name;
    private String tokenColor;
    private int currentSquareId;
    private int position;
    private PlayerStatus status;
    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCurrentSquareId() {
        return currentSquareId;
    }

    public void setCurrentSquareId(int currentSquareId) {
        this.currentSquareId = currentSquareId;
    }

    public String getTokenColor() {
        return tokenColor;
    }

    public void setTokenColor(String tokenColor) {
        this.tokenColor = tokenColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(int id, String name, String tokenColor, int squareId){
        this.id = id;
        this.name = name;
        this.tokenColor = tokenColor;
        this.currentSquareId = squareId;
        this.position = 0;
        this.status = PlayerStatus.NOT_READY;
        this.money = 2000;
    }
}

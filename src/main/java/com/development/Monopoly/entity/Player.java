package com.development.Monopoly.entity;

import com.development.Monopoly.Utils.PlayerStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Player {

    private @Id @GeneratedValue Long id;
    private String name;
    private String tokenColor;
    private Long currentSquareId;
    private int position;
    private PlayerStatus status;

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

    public Long getCurrentSquareId() {
        return currentSquareId;
    }

    public void setCurrentSquareId(Long currentSquareId) {
        this.currentSquareId = currentSquareId;
    }

    public String getTokenColor() {
        return tokenColor;
    }

    public void setTokenColor(String tokenColor) {
        this.tokenColor = tokenColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // do not delete this function
    private Player(){
        this.position = 0;
        this.status = PlayerStatus.NOT_READY;
    }

    public Player(String name, String tokenColor, Long squareId){
        this.name = name;
        this.tokenColor = tokenColor;
        this.currentSquareId = squareId;
        this.position = 0;
        this.status = PlayerStatus.NOT_READY;
    }
}

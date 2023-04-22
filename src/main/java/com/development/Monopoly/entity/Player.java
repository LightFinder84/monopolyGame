package com.development.Monopoly.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.message.Message;

import com.development.Monopoly.Utils.PlayerStatus;
import com.development.Monopoly.entity.space.Estate;
import com.development.Monopoly.entity.space.Space;
import com.development.Monopoly.exception.UnExpectedErrorException;

public class Player {

    private int id;
    private String name;
    private String tokenColor;
    private Long currentSquareId;
    private int position;
    private PlayerStatus status;
    private int money;
    private boolean host;
    private int stepToGo;
    private int moneyToPay;
    private List<Integer> personToPay;

    // constructor
    public Player(int id, String name, String tokenColor, Long squareId){
        this.id = id;
        this.name = name;
        this.tokenColor = tokenColor;
        this.currentSquareId = squareId;
        this.position = 0;
        this.status = PlayerStatus.NOT_READY;
        this.money = 2000;
        this.host = false;
        this.stepToGo = 0;
        this.moneyToPay = 0;
        personToPay = new ArrayList<>();

    }

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

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public int getStepToGo() {
        return stepToGo;
    }

    public void setStepToGo(int stepToGo) {
        this.stepToGo = stepToGo;
    }

    public Player quit(Table table) {
        return table.kickPlayer(this.id);
    }

    public void go(List<Space> spaces, Event event) {
        position += stepToGo;
        if(position > 39){
            position = position - 40;
            money += 200;
        }
        stepToGo = 0;

        Space space = spaces.get(position);
        if(space instanceof Estate){
            Estate estate = (Estate) space;
            if(estate.getOwner() != null){
                moneyToPay = estate.calculateRentMoney();
            }
        }

        String message = "---> " + this.name + " <--- Đã đi đến " + spaces.get(position).getName();
        event.setEventMessage(message);
    }

    public void buyEstate(List<Space> spaces, Event event) {
        Space space = spaces.get(position);
        Estate estate = null;
        if(space instanceof Estate == false){
            throw new UnExpectedErrorException("Chổ bạn đang đứng không thể mua");
        } else {
            estate = (Estate) space;
        }
        if (estate.getOwner() != null){
            throw new UnExpectedErrorException("Chổ bạn đang đứng có người mua rồi");
        }
        if(money < estate.getPriceForEstate()){
            throw new UnExpectedErrorException("Không đủ tiền mua");
        }
        estate.setOwner(this);
        money -= estate.getPriceForEstate();
        String message = "---> " + name + " <--- Đã mua " + estate.getName();
        event.setEventMessage(message);
    }

}

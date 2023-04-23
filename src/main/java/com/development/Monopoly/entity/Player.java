package com.development.Monopoly.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.message.Message;

import com.development.Monopoly.Utils.PlayerStatus;
import com.development.Monopoly.entity.space.Estate;
import com.development.Monopoly.entity.space.Property;
import com.development.Monopoly.entity.space.Space;
import com.development.Monopoly.exception.PlayerNotFoundException;
import com.development.Monopoly.exception.UnExpectedErrorException;

public class Player {

    private int id;
    private String name;
    private String tokenColor;
    private Long currentSquareId;
    private int currentPosition;
    private PlayerStatus status;
    private int money;
    private boolean host;
    private int stepToGo;
    private int rollAble;
    private List<Integer> listMoneyToPay;
    private List<Player> listPersonToPay;
    private int BusStationNumber;
    private List<Space> ownedSpaces;

    public Player findPersonToPayById(int playerId)
    {
        for (Player player : listPersonToPay) {
            if (player.getId() == playerId) return player;
        }
        throw new PlayerNotFoundException();
    }
    public void payMoney(int playerId, int money){
        Player personToPay = findPersonToPayById(playerId);
        if (this.getMoney() > money) throw new UnExpectedErrorException("Bạn trả dư tiền rồi, xin hãy nhập lại");
        else if (this.getMoney() < money) throw new UnExpectedErrorException("Bạn trả thiếu tiền rồi, xin hãy nhập lại");
        else {
            if (this.getMoney() < money){
                throw new UnExpectedErrorException("Không đủ tiền để trả"); // Thieu tien roi
            }
            else {
                personToPay.money += money;
                this.money -= money;
            }
            
        }
    }
    public int getBusStationNumber() {
        return BusStationNumber;
    }
    public void setBusStationNumber(int BusStationNumber) {
        this.BusStationNumber = BusStationNumber;
    }

    // constructor
    public Player(int id, String name, String tokenColor, Long squareId){
        this.id = id;
        this.name = name;
        this.tokenColor = tokenColor;
        this.currentSquareId = squareId;
        this.currentPosition = 0;
        this.status = PlayerStatus.NOT_READY;
        this.money = 2000;
        this.host = false;
        this.stepToGo = 0;
        this.listMoneyToPay = new ArrayList<>();
        this.listPersonToPay = new ArrayList<>();
        this.ownedSpaces = new ArrayList<>();
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

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
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

    public int getRollAble() {
        return rollAble;
    }

    public void setRollAble(int rollAble) {
        this.rollAble = rollAble;
    }

    public boolean addMoney(int money){
        this.money += money;
        return true;
    }

    public boolean subMoney(int money){
        if(this.money < money){
            return false;
        }
        this.money -= money;
        return true;
    }

    public Player quit(Table table) {
        return table.kickPlayer(this.id);
    }

    public void go(List<Space> spaces, Event event) {
        
        Space space = spaces.get(currentPosition);
        if(space instanceof Estate){
            Estate estate = (Estate) space;
            if(estate.getOwner() == this){
                if(estate.getNumberOfBuildings() == 0) estate.setNumberOfHousesCanBeBuild(1);
                if(estate.getNumberOfBuildings() > 0 && estate.getNumberOfBuildings() < 3) estate.setNumberOfHousesCanBeBuild( 4 - estate.getNumberOfBuildings());
                if(estate.getNumberOfBuildings() == 4) estate.setNumberOfHousesCanBeBuild(1);
            }
        }

        currentPosition += stepToGo;
        
        if(currentPosition > 39){
            currentPosition = currentPosition - 40;
            money += 200;
        }
        
        stepToGo = 0;
        
        // Space space = spaces.get(currentPosition);
        // if(space instanceof Estate){
        //     Estate estate = (Estate) space;
        //     if(estate.getOwner() != null){
        //         money = estate.calculateRentMoney(id); //chua check
        //     }
        // }

        String message = "---> " + this.name + " <--- Đã đi đến " + spaces.get(currentPosition).getName();
        event.setEventMessage(message);
    }

    public void buyProperty(List<Space> spaces, Event event) {
        Space space = spaces.get(currentPosition);
        Property property = null;
        if(space instanceof Property == false){
            throw new UnExpectedErrorException("Chổ bạn đang đứng không thể mua");
        } else {
            property = (Property) space;
        }
        if (property.getOwner() != null){
            throw new UnExpectedErrorException("Chổ bạn đang đứng có người mua rồi");
        }
        if(money < property.getPriceForProperty()){
            throw new UnExpectedErrorException("Không đủ tiền mua");
        }
        property.setOwner(this);
        money -= property.getPriceForProperty();
        this.ownedSpaces.add(property);
        String message = "---> " + name + " <--- Đã mua " + property.getName();
        event.setEventMessage(message);
    }

    public void finishTurn(Table table) {
        table.nextPlayer(this.id);
    }

    public void buyAHouse(List<Space> spaces, Event event) {
        Space space = spaces.get(currentPosition);
        Estate estate = null;
        if(space instanceof Estate == false){
            throw new UnExpectedErrorException("Chổ bạn đang đứng không thể xây nhà");
        } else {
            estate = (Estate) space;
        }

        Player owner = estate.getOwner();
        if(owner == null){
            throw new UnExpectedErrorException("Ô này bạn chưa mua, mua rồi mới được xây nhà nghen.");
        }

        if(this != owner){
            throw new UnExpectedErrorException("Chổ này không phải đất của bạn.");
        }

        if(this.money < estate.getPriceForBuilding()){
            throw new UnExpectedErrorException("Bạn không đủ tiền mua ô này.");
        }

        estate.addABuilding();
        this.money -= estate.getPriceForBuilding();
        String message = "---> " + name + " <--- đã mua thêm 1 nhà ở " + estate.getName() + " tăng số nhà lên " + estate.getNumberOfBuildings() + " nhà";
        event.setEventMessage(message);
    }

}

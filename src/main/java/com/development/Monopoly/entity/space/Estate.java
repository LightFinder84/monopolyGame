package com.development.Monopoly.entity.space;

import com.development.Monopoly.Utils.EstateColor;
import com.development.Monopoly.entity.Player;
import com.development.Monopoly.exception.UnExpectedErrorException;

public class Estate extends Property{
    
    private int priceForBuilding;
    private int numberOfBuildings;
    
    private EstateColor color;
    private int houseNumber;
    private int numberOfHousesCanBeBuild;

    public int getNumberOfHousesCanBeBuild() {
        return numberOfHousesCanBeBuild;
    }
    public void setNumberOfHousesCanBeBuild(int numberOfHousesCanBeBuild) {
        this.numberOfHousesCanBeBuild = numberOfHousesCanBeBuild;
    }
    public int getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
    public EstateColor getColor() {
        return color;
    }
    public void setColor(EstateColor color) {
        this.color = color;
    }
    
    public int getNumberOfBuildings() {

        return numberOfBuildings;
    }
    public int getPriceForBuilding() {
        return priceForBuilding;
    }
    
    public void setNumberOfBuildings(int numberOfBuildings) {
        this.numberOfBuildings = numberOfBuildings;
    }
    public void setPriceForBuilding(int priceForBuilding) {
        this.priceForBuilding = priceForBuilding;
    }
    
    public Estate(int id, String name, EstateColor color, int priceForEstate, int priceForBuilding){
        super(id, name, priceForEstate);
        this.numberOfBuildings = 0;
        this.priceForBuilding = priceForBuilding;
        this.color = color;
        this.numberOfHousesCanBeBuild = 0;
    }

    @Override
    public int calculateRentMoney(int playerId) {
        if (playerId == owner.getId()) {
            return 0;
        }
        else{
            if (this.getHouseNumber() == 1) return 10; // gia 1 can nha
            else if (this.getHouseNumber() == 2) return 20; // gia 2 can nha
            else if (this.getHouseNumber() == 3) return 30; // gia 3 can nha
            else if (this.getHouseNumber() == 4) return 40; // gia 4 can nha
            else return 50; // gia 5 can nha, hoac co the hieu la 1 khach san
        }
    }
    public void addABuilding() {
        if(numberOfHousesCanBeBuild > 0){
            numberOfBuildings ++;
            numberOfHousesCanBeBuild--;
        } else {
            throw new UnExpectedErrorException("Bạn không thể mua thêm nhà trong lượt này.");
        }
    }
}

package com.development.Monopoly.entity.space;

import com.development.Monopoly.Utils.EstateColor;
import com.development.Monopoly.entity.Player;

public class Estate extends Space{
    private int priceForEstate;
    private int priceForBuilding;
    private int numberOfBuildings;
    private Player owner;
    private EstateColor color;

    public EstateColor getColor() {
        return color;
    }
    public void setColor(EstateColor color) {
        this.color = color;
    }
    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public int getPriceForEstate() {
        return priceForEstate;
    }
    public int getNumberOfBuildings() {

        return numberOfBuildings;
    }
    public int getPriceForBuilding() {
        return priceForBuilding;
    }
    public void setPriceForEstate(int priceForEstate) {
        this.priceForEstate = priceForEstate;
    }
    public void setNumberOfBuildings(int numberOfBuildings) {
        this.numberOfBuildings = numberOfBuildings;
    }
    public void setPriceForBuilding(int priceForBuilding) {
        this.priceForBuilding = priceForBuilding;
    }
    
    public Estate(int id, String name, EstateColor color, int priceForEstate, int priceForBuilding){
        super(id, name);
        this.numberOfBuildings = 0;
        this.priceForBuilding = priceForBuilding;
        this.priceForEstate = priceForEstate;
        this.color = color;
    }
    

    public int calculateRentMoney(){
        return 0;
    }

}

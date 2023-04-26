package com.development.Monopoly.entity.space;

import com.development.Monopoly.Utils.EstateColor;
import com.development.Monopoly.exception.UnExpectedErrorException;

public class Estate extends Property {

    private int priceForBuilding;
    private int numberOfBuildings;

    private EstateColor color;
    private int numberOfHousesCanBeBuild;

    public int getNumberOfHousesCanBeBuild() {
        return numberOfHousesCanBeBuild;
    }

    public void setNumberOfHousesCanBeBuild(int numberOfHousesCanBeBuild) {
        this.numberOfHousesCanBeBuild = numberOfHousesCanBeBuild;
    }

    public int getNumberOfBuildings() {
        return numberOfBuildings;
    }

    public void setNumberOfBuildings(int numberOfBuildings) {
        this.numberOfBuildings = numberOfBuildings;
    }

    public EstateColor getColor() {
        return color;
    }

    public void setColor(EstateColor color) {
        this.color = color;
    }

    public int getPriceForBuilding() {
        return priceForBuilding;
    }

    public void setPriceForBuilding(int priceForBuilding) {
        this.priceForBuilding = priceForBuilding;
    }

    public Estate(int id, String name, EstateColor color, int priceForEstate, int priceForBuilding) {
        super(id, name, priceForEstate);
        this.numberOfBuildings = 0;
        this.priceForBuilding = priceForBuilding;
        this.color = color;
        this.numberOfHousesCanBeBuild = 0;
    }

    @Override
    public int calculateRentMoney() {
        if (this.id == 1) {
            if (this.getNumberOfBuildings() == 0) return 2;
            else if (this.getNumberOfBuildings() == 1) return 10; 
            else if (this.getNumberOfBuildings() == 2) return 30;
            else if (this.getNumberOfBuildings() == 3) return 90;
            else if (this.getNumberOfBuildings() == 4) return 160;
            else return 250;
        }
        else if (this.id == 3) {
            if (this.getNumberOfBuildings() == 0) return 4;
            else if (this.getNumberOfBuildings() == 1) return 20; 
            else if (this.getNumberOfBuildings() == 2) return 60;
            else if (this.getNumberOfBuildings() == 3) return 180;
            else if (this.getNumberOfBuildings() == 4) return 320;
            else return 450;
        }
        else if (this.id == 5) {

        }
        else if (this.id == 6 || this.id == 8) {
            if (this.getNumberOfBuildings() == 0) return 6;
            else if (this.getNumberOfBuildings() == 1) return 30; 
            else if (this.getNumberOfBuildings() == 2) return 90;
            else if (this.getNumberOfBuildings() == 3) return 270;
            else if (this.getNumberOfBuildings() == 4) return 400;
            else return 550;
        }
        else if (this.id == 9) {
            if (this.getNumberOfBuildings() == 0) return 8;
            else if (this.getNumberOfBuildings() == 1) return 40; 
            else if (this.getNumberOfBuildings() == 2) return 100;
            else if (this.getNumberOfBuildings() == 3) return 300;
            else if (this.getNumberOfBuildings() == 4) return 450;
            else return 600;
        }
        else if (this.id == 11 || this.id == 13) {
            if (this.getNumberOfBuildings() == 0) return 10;
            else if (this.getNumberOfBuildings() == 1) return 50; 
            else if (this.getNumberOfBuildings() == 2) return 150;
            else if (this.getNumberOfBuildings() == 3) return 450;
            else if (this.getNumberOfBuildings() == 4) return 625;
            else return 750;
        }
        else if (this.id == 14) {
            if (this.getNumberOfBuildings() == 0) return 12;
            else if (this.getNumberOfBuildings() == 1) return 60; 
            else if (this.getNumberOfBuildings() == 2) return 180;
            else if (this.getNumberOfBuildings() == 3) return 500;
            else if (this.getNumberOfBuildings() == 4) return 700;
            else return 900;
        }
        else if (this.id == 16 || this.id == 18) {
            if (this.getNumberOfBuildings() == 0) return 14;
            else if (this.getNumberOfBuildings() == 1) return 70; 
            else if (this.getNumberOfBuildings() == 2) return 200;
            else if (this.getNumberOfBuildings() == 3) return 550;
            else if (this.getNumberOfBuildings() == 4) return 750;
            else return 950;
        }
        else if (this.id == 19) {
            if (this.getNumberOfBuildings() == 0) return 16;
            else if (this.getNumberOfBuildings() == 1) return 80; 
            else if (this.getNumberOfBuildings() == 2) return 220;
            else if (this.getNumberOfBuildings() == 3) return 600;
            else if (this.getNumberOfBuildings() == 4) return 800;
            else return 1000;
        }
        else if (this.id == 21 || this.id == 23) {
            if (this.getNumberOfBuildings() == 0) return 18;
            else if (this.getNumberOfBuildings() == 1) return 90; 
            else if (this.getNumberOfBuildings() == 2) return 250;
            else if (this.getNumberOfBuildings() == 3) return 700;
            else if (this.getNumberOfBuildings() == 4) return 875;
            else return 1050;
        }
        else if (this.id == 24) {
            if (this.getNumberOfBuildings() == 0) return 20;
            else if (this.getNumberOfBuildings() == 1) return 100; 
            else if (this.getNumberOfBuildings() == 2) return 300;
            else if (this.getNumberOfBuildings() == 3) return 750;
            else if (this.getNumberOfBuildings() == 4) return 925;
            else return 1100;
        }
        else if (this.id == 26 || this.id == 27) {
            if (this.getNumberOfBuildings() == 0) return 22;
            else if (this.getNumberOfBuildings() == 1) return 110; 
            else if (this.getNumberOfBuildings() == 2) return 330;
            else if (this.getNumberOfBuildings() == 3) return 800;
            else if (this.getNumberOfBuildings() == 4) return 975;
            else return 1150;
        }
        else if (this.id == 29) {
            if (this.getNumberOfBuildings() == 0) return 24;
            else if (this.getNumberOfBuildings() == 1) return 120; 
            else if (this.getNumberOfBuildings() == 2) return 360;
            else if (this.getNumberOfBuildings() == 3) return 850;
            else if (this.getNumberOfBuildings() == 4) return 1025;
            else return 1200;
        }
        else if (this.id == 31 || this.id == 32) {
            if (this.getNumberOfBuildings() == 0) return 26;
            else if (this.getNumberOfBuildings() == 1) return 130; 
            else if (this.getNumberOfBuildings() == 2) return 390;
            else if (this.getNumberOfBuildings() == 3) return 900;
            else if (this.getNumberOfBuildings() == 4) return 1100;
            else return 1275;
        }
        else if (this.id == 34) {
            if (this.getNumberOfBuildings() == 0) return 28;
            else if (this.getNumberOfBuildings() == 1) return 150; 
            else if (this.getNumberOfBuildings() == 2) return 450;
            else if (this.getNumberOfBuildings() == 3) return 1000;
            else if (this.getNumberOfBuildings() == 4) return 1200;
            else return 1400;
        }
        return 0;
    }
    @Override
    public int getSellPrice() {
        int priceForEstateAndAllBuildings = this.getPriceForBuilding() * this.getNumberOfBuildings()/2 + this.getPriceForProperty()/2;
        return priceForEstateAndAllBuildings;
    }

    public void addABuilding() {
        if (numberOfHousesCanBeBuild > 0) {
            numberOfBuildings++;
            numberOfHousesCanBeBuild--;
        } else {
            throw new UnExpectedErrorException("Bạn không thể mua thêm nhà trong lượt này.");
        }
    }
    public void deleteABuilding(){
        if (numberOfHousesCanBeBuild <= 0) throw new UnExpectedErrorException("Không còn nhà để bán");
        else {
            numberOfBuildings--;
            numberOfHousesCanBeBuild++;
        }
    }
    public void deleteAllBuildings(){
        if (numberOfHousesCanBeBuild <= 0) throw new UnExpectedErrorException("Không còn nhà để bán");
        else {
            numberOfBuildings = 0;
            numberOfHousesCanBeBuild = 1; //Chỉ có thể mua 1 căn nhà vào lần đầu tiên
        }
    }
    
}

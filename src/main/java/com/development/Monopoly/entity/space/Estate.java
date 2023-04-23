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

        if (this.getNumberOfBuildings() == 1)
            return 10; // gia 1 can nha
        else if (this.getNumberOfBuildings() == 2)
            return 20; // gia 2 can nha
        else if (this.getNumberOfBuildings() == 3)
            return 30; // gia 3 can nha
        else if (this.getNumberOfBuildings() == 4)
            return 40; // gia 4 can nha
        else
            return 50; // gia 5 can nha, hoac co the hieu la 1 khach san
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
    @Override
    public int getSellPrice() {
        int priceForEstateAndAllBuildings = this.getPriceForBuilding() * this.getNumberOfBuildings()/2 + this.getPriceForProperty()/2;
        return priceForEstateAndAllBuildings;
    }
}

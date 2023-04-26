package com.development.Monopoly.entity.space;

import java.util.List;

import com.development.Monopoly.entity.Player;

public class Company extends Property{

    public Company(int id, String name, int priceForProperty){
        super(id, name, priceForProperty);
    }

    @Override
    public int calculateRentMoney() {
        return 0;
    }
    @Override
    public int getSellPrice() {
        return this.getPriceForProperty()/2;
    }

    public int getRentMoney(Player owner){
        List<Property> ownedProperties = owner.getOwnedProperty();
        int countStation = 0;
        int countCompany = 0;
        for (Property property : ownedProperties) {
            if(property instanceof BusStation) countStation ++;
            if(property instanceof Company) countCompany ++;
        } 
        return 50 * (countCompany + countStation); 
    }

    @Override
    public String getInfo() {
        String ownerName = (this.owner != null) ? this.owner.getName() : "Chưa có";
        String nameRow = "<div class=\"w-100 d-flex justify-content-center\"><p class=\"\">"+ this.name +"</p></div>";
        String ownerRow = "<div class=\"row\"><div class=\"col\">Chủ:</div><div class=\"col\">"+ ownerName +"</div></div>";
        return nameRow + ownerRow;
    }
}

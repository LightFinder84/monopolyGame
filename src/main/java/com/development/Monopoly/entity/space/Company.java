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
    public int getSellPrice() {
        return this.getPriceForProperty()/2;
    }
}

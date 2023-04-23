package com.development.Monopoly.entity.space;

import java.util.List;

import com.development.Monopoly.entity.Player;

public class BusStation extends Property{

    public BusStation(int id, String name, int price){
        super(id, name, price);
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
}

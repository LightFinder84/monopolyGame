package com.development.Monopoly.entity.space;

import com.development.Monopoly.entity.Player;

public class Company extends Property{

    public Company(int id, String name, int priceForProperty){
        super(id, name, priceForProperty);
    }

    @Override
    public int calculateRentMoney(int playerId) {
        if (playerId == owner.getId()) {
            return 0;
        }
        else{
            return 50*owner.getBusStationNumber(); //gia mac dinh la 50
        }
    }

    @Override
    public int getSellPrice() {
        return this.getPriceForProperty()/2;
    }
}

package com.development.Monopoly.entity.space;

import com.development.Monopoly.entity.Player;

public class BusStation extends Property{

    public BusStation(int id, String name, int price){
        super(id, name, price);
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
}

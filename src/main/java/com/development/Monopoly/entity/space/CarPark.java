package com.development.Monopoly.entity.space;

public class CarPark extends Space{
    public CarPark(int id, String name){
        super(id, name);
    }

    @Override
    public int calculateRentMoney(int playerId) {
        return 0;
    }
}

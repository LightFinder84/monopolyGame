package com.development.Monopoly.entity.space;

public class Chance extends Space{
    public Chance(int id, String name){
        super(id, name);
    }

    @Override
    public int calculateRentMoney(int playerId) {
        return 0;
    }
}

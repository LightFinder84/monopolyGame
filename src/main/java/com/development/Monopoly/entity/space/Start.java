package com.development.Monopoly.entity.space;

public class Start extends Space{
    public Start(int id, String name){
        super(id, name);
    }
    @Override
    public int calculateRentMoney(int playerId) {
        return 0;
    }
}

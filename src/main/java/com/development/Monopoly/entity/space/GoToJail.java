package com.development.Monopoly.entity.space;

public class GoToJail extends Space{
    public GoToJail(int id, String name){
        super(id, name);
    }

    @Override
    public int calculateRentMoney(int playerId) {
        return 0;
    }
}

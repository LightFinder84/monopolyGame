package com.development.Monopoly.entity.space;

public class SpecialTax extends Space{
    public SpecialTax(int id, String name){
        super(id, name);
    }

    @Override
    public int calculateRentMoney(int playerId) {
        return 200;
    }
}

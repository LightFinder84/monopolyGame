package com.development.Monopoly.entity.space;

public class Tax extends Space{
    public Tax(int id, String name){
        super(id, name);
    }
    
    public int getTaxFee(int userMoney){
        return userMoney / 10;
    }
}

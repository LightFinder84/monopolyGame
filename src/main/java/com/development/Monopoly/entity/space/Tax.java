package com.development.Monopoly.entity.space;

import com.development.Monopoly.entity.Player;

public class Tax extends Space{
    public Tax(int id, String name){
        super(id, name);
    }
    @Override
    public int calculateRentMoney(int playerId) {
        Player player = this.findVisitorById(playerId);
        int money = player.getMoney();
        return money/10;
    }
}

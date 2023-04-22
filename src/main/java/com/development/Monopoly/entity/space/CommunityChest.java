package com.development.Monopoly.entity.space;

public class CommunityChest extends Space{
    public CommunityChest(int id, String name){
        super(id, name);
    }

    @Override
    public int calculateRentMoney(int playerId) {
        return 0;
    }
}

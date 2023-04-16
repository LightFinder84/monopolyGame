package com.development.Monopoly.entity.space;

import com.development.Monopoly.entity.Player;

public class Company extends Space{
    private Player owner;

    
    public Player getOwner() {
        return owner;
    }


    public void setOwner(Player owner) {
        this.owner = owner;
    }


    public Company(int id, String name){
        super(id, name);
    }
}

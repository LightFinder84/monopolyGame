package com.development.Monopoly.entity.space;

import com.development.Monopoly.entity.Player;

public abstract class Property extends Space{

    protected Player owner;
    protected int priceForProperty;

    public int getPriceForProperty() {
        return priceForProperty;
    }

    public void setPriceForProperty(int priceForProperty) {
        this.priceForProperty = priceForProperty;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Property(int id, String name, int priceForProperty) {
        super(id, name);
        this.priceForProperty = priceForProperty;
    }
    
}

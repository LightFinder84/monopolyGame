package com.development.Monopoly.entity.space;

import java.util.List;

import com.development.Monopoly.entity.Player;
import com.development.Monopoly.exception.PlayerNotFoundException;

//khoa
public abstract class Space {
    protected int id; //from 0 to 39
    protected String name;
    // protected List<Player> vistors;

    
    public Space(int id, String name){
        this.id = id;
        this.name = name;
    }

    public abstract String getInfo();

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    // public Player findVisitorById(int playerId)
    // {
    //     for (Player player : vistors) {
    //         if (player.getId() == playerId) return player;
    //     }
    //     throw new PlayerNotFoundException();
    // }
    

}
//Company, chance space, community chest space, jail, go to jail, tax, special tax, car park, estate, bus station
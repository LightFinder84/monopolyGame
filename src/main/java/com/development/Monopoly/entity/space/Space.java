package com.development.Monopoly.entity.space;

import java.util.List;

import com.development.Monopoly.entity.Player;
import com.development.Monopoly.exception.PlayerNotFoundException;

//khoa
public abstract class Space {
    protected int id; //from 0 to 39
    protected String name;
    protected List<Player> vistors;

    public Space(int id, String name){
        this.id = id;
        this.name = name;
    }
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

    public Player findVisitorById(int playerId)
    {
        for (Player player : vistors) {
            if (player.getId() == playerId) return player;
        }
        throw new PlayerNotFoundException();
    }
    // public void spaceType(int id){
    //     int estateid_row1[] = {1,3,6,8,9};
    //     int  estateid_row2[] = {11,13,14,16,18,19};
    //     int  estateid_row3[] = {21,23,24,26,27,29};
    //     int  estateid_row4[] = {31,32,34,37,39};
    //     int  bus_station_id[] = {5, 15, 25, 35};
    //     int  company_id[] = {12};
    //     int chance_id[] = {7, 22, 36};
    //     int community_chance_id[] ={2, 17, 33};
    // }
}
//Company, chance space, community chest space, jail, go to jail, tax, special tax, car park, estate, bus station
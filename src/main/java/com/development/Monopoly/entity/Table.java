package com.development.Monopoly.entity;

import java.util.ArrayList;
import java.util.List;

import com.development.Monopoly.exception.DuplicateColorException;
import com.development.Monopoly.exception.DuplicateNameException;
import com.development.Monopoly.exception.GameFullException;
import com.development.Monopoly.exception.PlayerNotFoundException;
import com.development.Monopoly.exception.UnExpectedErrorException;


// This class stands for a game table
public class Table {

    private  int id;

    private String name;

    private String password;

    private List<Player> playerList;

    // constructor
    private Table(int id, String name, String password){
        this.id = id;
        playerList = new ArrayList<>();
        this.name = name;
        this.password = password;
    };

    public Player findPlayerById(int id){
        for (Player player : playerList) {
            if(player.getId() == id){
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player addPlayer(Player newPlayer){
        if(playerList.size() == 4){
            throw new GameFullException();
        }

        for (Player player : playerList) {
            if (player.getName().equals(newPlayer.getName())) {
                throw new DuplicateNameException();
            }
            if(player.getTokenColor().equals(newPlayer.getTokenColor())){
                throw new DuplicateColorException();
            }
        }

        playerList.add(newPlayer);
        return newPlayer;
    }

    public int countPlayer(){
        return playerList.size();
    }

    public Player kickPlayer(int playerId) {

        Player player = findPlayerById(playerId);

        if (playerList.remove(player) == false) {
            throw new UnExpectedErrorException(2);
        }

        return player;
    }

}

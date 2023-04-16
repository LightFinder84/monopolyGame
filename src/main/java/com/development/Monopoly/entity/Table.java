package com.development.Monopoly.entity;

import java.util.ArrayList;
import java.util.List;

import com.development.Monopoly.Utils.GameState;
import com.development.Monopoly.Utils.PlayerStatus;
import com.development.Monopoly.controller.TableController;
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

    private int hostId;

    private GameState state;

    private List<Player> playerList;

    private String lastEventMessage;

    // constructor
    private Table(int id, String name, String password){
        this.id = id;
        playerList = new ArrayList<>();
        this.name = name;
        this.password = password;
        hostId = 0;
        state = GameState.NOT_STARTED;
        lastEventMessage = "Sẵn sàng và chờ chủ phòng bắt đầu.";
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

    public int gethostId() {
        return hostId;
    }

    public void sethostId(int hostId) {
        this.hostId = hostId;
    }


    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public String getLastEventMessage() {
        return lastEventMessage;
    }

    public void setLastEventMessage(String lastEventMessage) {
        this.lastEventMessage = lastEventMessage;
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

        if(this.state == GameState.STARTED){
            throw new UnExpectedErrorException("Trò chơi đang diễn ra.");
        }

        if(playerList.size() == 0){
            hostId = newPlayer.getId();
            newPlayer.setHost(true);
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

        if(playerList.size() == 0){
            TableController.deleteTable(this.id);
        } else {
            Player newHost = playerList.get(0);
            newHost.setHost(true);
        }

        return player;
    }

    public void startGame() {
        for (Player player : playerList) {
            if(player.getStatus() != PlayerStatus.READY){
                throw new UnExpectedErrorException("Người chơi chưa sẵn sàng.");
            }
        }
        this.state = GameState.STARTED;
        lastEventMessage = "Trò chơi đã được bắt đầu."; 
    }

}
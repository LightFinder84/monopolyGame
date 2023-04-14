package com.development.Monopoly.entity;

import java.util.ArrayList;
import java.util.List;

import com.development.Monopoly.exception.DuplicateColorException;
import com.development.Monopoly.exception.DuplicateNameException;
import com.development.Monopoly.exception.GameFullException;
import com.development.Monopoly.repository.PlayerRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


// This class stands for a game table
@Entity
public class Square {

    private @Id @GeneratedValue Long id;

    private String name;

    private String password;

    private List<Long> listPlayerId;
    private List<String> listName;
    private List<String> listTokenColor;

    // dont delete this method
    private Square(){
        listPlayerId = new ArrayList<Long>();
        listName = new ArrayList<String>();
        listTokenColor = new ArrayList<String>();
    };

    public Square(String name){
        this.name = name;
        this.password = "";
        listPlayerId = new ArrayList<Long>();
        listName = new ArrayList<String>();
        listTokenColor = new ArrayList<String>();
    }

    public Square(String name, String password){
        this.name = name;
        this.password = password;
        listPlayerId = new ArrayList<Long>();
        listName = new ArrayList<String>();
        listTokenColor = new ArrayList<String>();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addPlayer(Long playerId, String name, String tokenColor, PlayerRepository playerRepository){
        if(listPlayerId.size() == 4){
            throw new GameFullException();
        } 

        if(listName.contains(name)){
            playerRepository.deleteById(playerId);
            throw new DuplicateNameException();
        }

        if(listTokenColor.contains(tokenColor)){
            playerRepository.deleteById(playerId);
            throw new DuplicateColorException();
        }

        listPlayerId.add(playerId);
        listName.add(name);
        listTokenColor.add(tokenColor);
    }

    public int countPlayer(){
        return listPlayerId.size();
    }

    public List<Long> getListPlayerId(){
        return listPlayerId;
    }

    public void setListPlayerId(List<Long> listID){
        this.listPlayerId = listID;
    }

}

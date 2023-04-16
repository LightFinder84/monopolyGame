package com.development.Monopoly.controller;

import com.development.Monopoly.Utils.GameState;
import com.development.Monopoly.Utils.PlayerStatus;
import com.development.Monopoly.entity.Player;
import com.development.Monopoly.entity.Table;
import com.development.Monopoly.exception.GameFullException;
import com.development.Monopoly.exception.PasswordIncorrectException;
import com.development.Monopoly.exception.PlayerNotFoundException;
import com.development.Monopoly.exception.SquareNotFoundException;
import com.development.Monopoly.exception.UnExpectedErrorAdvice;
import com.development.Monopoly.exception.UnExpectedErrorException;

import org.hibernate.tool.schema.extract.spi.TableInformation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TableController {

    private static int newPlayerId;
    private static int lastTableId;
    private static List<Table> tableList;

    private static Table findTableById(int id){
        for (Table table : tableList) {
            if(table.getId() == id){
                return table;
            }
        }
        throw new SquareNotFoundException(id);
    }

    public TableController() {
        newPlayerId = 1;
        lastTableId = 0;
        tableList = new ArrayList<>();
    }

    // Create table
    @PostMapping("/create-table")
    public static Table newTable (@RequestBody Table table){
        lastTableId++;
        table.setId(lastTableId);
        tableList.add(table);
        return table;
    }

    // get all tables
    @GetMapping("/tables")
    public static List<Table> all(){
        return tableList;
    }

    // get a table
    @GetMapping("/tables/{id}")
    public static Table getOneTable(@PathVariable int id){
        return findTableById(id);
    }

    
    // join a table
    @PostMapping("/join")
    public static Table join(@RequestBody Table tableToJoin){
        Table table = findTableById(tableToJoin.getId());

        String squarePassowrd = table.getPassword();
        String joinPassword = tableToJoin.getPassword();

        if(!squarePassowrd.equals(joinPassword)){
            throw new PasswordIncorrectException();
        }

        if (table.countPlayer() == 4) {
            throw new GameFullException();
        }

        if(table.getState() == GameState.STARTED){
            throw new UnExpectedErrorException("Trò chơi đã được bắt đầu.");
        }

        return table;
    }

    // add player to a table
    @PostMapping("/tables/{tableId}/add-player")
    public static Player addPlayer(@RequestBody Player newPlayer, @PathVariable int tableId){
        Table table = findTableById(tableId);

        newPlayer.setId(newPlayerId);
        Player player = table.addPlayer(newPlayer);
        newPlayerId++;
        return player;
    }

    // get players in a table
    @GetMapping("/tables/{tableId}/get-players")
    public static List<Player> getPlayersOnTable(@PathVariable int tableId){
        Table table = findTableById(tableId);
        return table.getPlayerList();
    }

    // get players in a table
    @GetMapping("/tables/{tableId}/syncronize")
    public static Table syncronize(@PathVariable int tableId){
        return findTableById(tableId);
    }

    // delete a player in a table (kick)
    @DeleteMapping("/tables/{tableId}/kick/{playerId}")
    public static Player kickPlayer(@PathVariable int tableId, @PathVariable int playerId){
        Table table = findTableById(tableId);

        return table.kickPlayer(playerId);
    }

    // player get ready
    @GetMapping("tables/{tableId}/{playerId}/ready")
    public static void getReady(@PathVariable int tableId, @PathVariable int playerId){
        Table table = findTableById(tableId);

        Player player = table.findPlayerById(playerId);

        player.setStatus(PlayerStatus.READY);
    }

    // player quit the game
    @GetMapping("tables/{tableId}/{playerId}/quit")
    public static Player quit(@PathVariable int tableId, @PathVariable int playerId){
        Table table = findTableById(tableId);
        Player player = table.findPlayerById(playerId);
        return player.quit(table);
    }

    // all players left the game
    public static void deleteTable(int id) {
        Table table = findTableById(id);
        if(tableList.remove(table) == false ){
            throw new UnExpectedErrorException(12);
        }
    }

    // bắt đầu trò chơi
    @GetMapping("/tables/{tableId}/start")
    public static void startGame(@PathVariable int tableId){
        Table table = findTableById(tableId);
        table.startGame();
    }
}
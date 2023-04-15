package com.development.Monopoly.controller;

import com.development.Monopoly.entity.Player;
import com.development.Monopoly.entity.Square;
import com.development.Monopoly.entity.User;
import com.development.Monopoly.exception.GameFullException;
import com.development.Monopoly.exception.PasswordIncorrectException;
import com.development.Monopoly.exception.SquareNotFoundException;
import com.development.Monopoly.repository.PlayerRepository;
import com.development.Monopoly.repository.SquareRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SquareController {
    private final SquareRepository repository;
    private final PlayerRepository playerRepository;
    private int lastUserId;
    private final List<User> userList;

    public SquareController(SquareRepository repository, PlayerRepository playerRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
        this.userList = new ArrayList<>();
        lastUserId = 0;
    }

    @PostMapping("/add-user")
    User addUser(@RequestBody User user){
        lastUserId++;
        user.setId(lastUserId);
        userList.add(user);
        return user;
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userList;
    }
    

    @GetMapping("/squares")
    List<Square> all(){
        return repository.findAll();
    }

    @GetMapping("/squares/{id}")
    Square one(@PathVariable Long id){
        return repository.findById(id)
            .orElseThrow(()-> new SquareNotFoundException(id));
    }

    @PostMapping("/squares")
    Square newSquare(@RequestBody Square square){
        return repository.save(square);
    }

    @PostMapping("/join")
    Square join(@RequestBody Square joinSquare){
        Square square = repository.findById(joinSquare.getId())
            .orElseThrow(() -> new SquareNotFoundException(joinSquare.getId()));

        String squarePassowrd = square.getPassword();
        String joinPassword = joinSquare.getPassword();

        if(!squarePassowrd.equals(joinPassword)){
            throw new PasswordIncorrectException();
        }

        if (square.countPlayer() == 4) {
            throw new GameFullException();
        }

        return square;
    }

    @PostMapping("/squares/{squareId}/add-player")
    Player addPlayer(@RequestBody Player newPlayer, @PathVariable Long squareId){
        Square square = repository.findById(squareId)
            .orElseThrow(() -> new SquareNotFoundException(squareId));

        if(newPlayer.getId() == null){
            Player player = playerRepository.save(newPlayer);
            square.addPlayer(player.getId(), player.getName(), player.getTokenColor(), playerRepository);
            repository.save(square);
            return player;
        } else {
            Player player = playerRepository.findById(newPlayer.getId())
            .map(element -> {
                element.setName(newPlayer.getName());
                element.setTokenColor(newPlayer.getTokenColor());
                element.setCurrentSquareId(newPlayer.getCurrentSquareId());
                return element;
            })
            .orElseGet(()->{
                return playerRepository.save(newPlayer);
            });
            square.addPlayer(player.getId(), player.getName(), player.getTokenColor(), playerRepository);
            repository.save(square);
            return player;
        }
    }


    @GetMapping("/squares/{squareId}/get-players")
    public List<Player> getPlayersOnTable(@PathVariable Long squareId){
        return playerRepository.findAll().stream()
            .filter(player -> {
                if(player.getCurrentSquareId() == squareId) return true;
                else return false;
            })
            .collect(Collectors.toList());
    }

    @DeleteMapping("/squares/{squareId}/kick/{playerId}")
    public Player kickPlayer(@PathVariable Long squareId, @PathVariable Long playerId){
        Square square = repository.findById(squareId).get();
        Player player =  square.kickPlayer(playerId, playerRepository);
        repository.save(square);
        return player;
    }
}

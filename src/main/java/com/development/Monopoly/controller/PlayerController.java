package com.development.Monopoly.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.development.Monopoly.entity.Player;
import com.development.Monopoly.repository.PlayerRepository;

@RestController
public class PlayerController {

    private final PlayerRepository repository;

    public PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/players")
    List<Player> all() {
        return repository.findAll();
    }

    @PostMapping("/players")
    Player newPlayer(@RequestBody Player player) {
        return repository.save(player);
    }

    @PutMapping("/players/{id}")
    Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {
        return repository.findById(id)
                .map(player -> {
                    player.setName(newPlayer.getName());
                    return repository.save(player);
                })
                .orElseGet(() -> {
                    return repository.save(newPlayer);
                });
    }
}

package com.development.Monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.development.Monopoly.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}

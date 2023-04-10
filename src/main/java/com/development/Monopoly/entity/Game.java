package com.development.Monopoly.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Game {
    
    private @Id @GeneratedValue Long id;
    
}

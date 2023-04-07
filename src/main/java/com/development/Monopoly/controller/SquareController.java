package com.development.Monopoly.controller;

import com.development.Monopoly.entity.Square;
import com.development.Monopoly.repository.SquareRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SquareController {
    private final SquareRepository repository;

    public SquareController(SquareRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/squares")
    List<Square> all(){
        return repository.findAll();
    }

    @PostMapping("/squares")
    Square newSquare(@RequestBody Square square){
        return repository.save(square);
    }
}

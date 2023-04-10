package com.development.Monopoly.controller;

import com.development.Monopoly.entity.Square;
import com.development.Monopoly.exception.PasswordIncorrectException;
import com.development.Monopoly.exception.SquareNotFoundException;
import com.development.Monopoly.repository.SquareRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return square;
    }

}

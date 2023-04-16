package com.development.Monopoly.entity;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int dice1;
    private int dice2;

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public void roll(){
        this.dice1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        this.dice2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }

    public int getSum(){
        return dice1 + dice2;
    }

    public boolean duplicateDice(){
        return dice1 == dice2;
    }
}

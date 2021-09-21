package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        isEnemy = false;
        health = 10;
        damage = 5;
    }

    public String getTileName() {
        return "player";
    }
}

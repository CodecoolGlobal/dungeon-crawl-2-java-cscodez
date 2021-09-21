package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Mandalorian extends Actor {
    public Mandalorian(Cell cell) {
        super(cell);
        isEnemy = true;
        health = 10;
        damage = 5;
    }

    @Override
    public String getTileName() {
        return "mandalorian";
    }
}
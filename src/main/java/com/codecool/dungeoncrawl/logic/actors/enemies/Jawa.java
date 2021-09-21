package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Jawa extends Actor {
    public Jawa(Cell cell) {
        super(cell);
        isEnemy = true;
        health = 5;
        damage = 2;
    }

    @Override
    public String getTileName() {
        return "jawa";
    }
}
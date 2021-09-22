package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Jawa extends Enemy {
    public Jawa(Cell cell) {
        super(cell);
        health = 5;
        damage = 2;
    }

    @Override
    public String getTileName() {
        return "jawa";
    }
}
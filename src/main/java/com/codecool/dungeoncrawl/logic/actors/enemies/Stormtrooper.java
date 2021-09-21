package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Stormtrooper extends Actor {
    public Stormtrooper(Cell cell) {
        super(cell);
        isEnemy = true;
        health = 10;
        damage = 2;
    }

    @Override
    public String getTileName() {
        return "stormtrooper";
    }
}

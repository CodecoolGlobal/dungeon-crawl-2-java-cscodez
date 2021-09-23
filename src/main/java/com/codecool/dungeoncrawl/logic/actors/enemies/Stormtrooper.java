package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Stormtrooper extends Enemy {
    public Stormtrooper(Cell cell) {
        super(cell);
        health = 10;
        damage = 2;
    }

    @Override
    public String getTileName() {
        return "stormtrooper";
    }
}

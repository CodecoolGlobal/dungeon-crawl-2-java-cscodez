package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;

public class Stormtrooper extends Enemy {
    public Stormtrooper(Cell cell) {
        super(cell);
        health = Enemies.STORMTROOPER.getHealth();
        damage = Enemies.STORMTROOPER.getDamage();
    }

    @Override
    public String getTileName() {
        return "stormtrooper";
    }
}

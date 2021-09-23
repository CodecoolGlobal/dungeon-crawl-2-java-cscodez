package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;

public class GreenBrute extends Enemy {
    public GreenBrute (Cell cell) {
        super(cell);
        health = Enemies.GREENBRUTE.getHealth();
        damage = Enemies.GREENBRUTE.getDamage();
    }

    @Override
    public String getTileName() {
        return "greenBrute";
    }
}
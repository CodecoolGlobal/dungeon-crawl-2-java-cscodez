package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class GreenBrute extends Actor {
    public GreenBrute (Cell cell) {
        super(cell);
        isEnemy = true;
        health = 20;
        damage = 10;
    }

    @Override
    public String getTileName() {
        return "greenBrute";
    }
}
package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Enemy extends Actor {
    public Enemy(Cell cell) {
        super(cell);
        isEnemy = true;
    }
}

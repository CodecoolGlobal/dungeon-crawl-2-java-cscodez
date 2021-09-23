package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;

public class Jawa extends Enemy {
    public Jawa(Cell cell) {
        super(cell);
        health = Enemies.JAWA.getHealth();
        damage = Enemies.JAWA.getDamage();
    }

    public void move(int dx, int dy) {
        // hehe :3
    }

    @Override
    public String getTileName() {
        return Enemies.JAWA.getTileName();
    }
}
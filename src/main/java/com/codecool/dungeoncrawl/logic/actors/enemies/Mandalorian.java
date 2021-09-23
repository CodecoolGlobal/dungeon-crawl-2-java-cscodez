package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mandalorian extends FlyingEnemy {
    public Mandalorian(Cell cell) {
        super(cell);
        health = Enemies.MANDALORIAN.getHealth();
        damage = Enemies.MANDALORIAN.getDamage();
    }


    @Override
    public String getTileName() {
        return Enemies.MANDALORIAN.getTileName();
    }
}
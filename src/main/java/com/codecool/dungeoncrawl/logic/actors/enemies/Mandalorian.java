package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mandalorian extends Enemy {
    public Mandalorian(Cell cell) {
        super(cell);
        health = Enemies.MANDALORIAN.getHealth();
        damage = Enemies.MANDALORIAN.getDamage();
    }

    public void move(int dx, int dy) {
      // hehe :3
    }

    @Override
    public String getTileName() {
        return "mandalorian";
    }
}
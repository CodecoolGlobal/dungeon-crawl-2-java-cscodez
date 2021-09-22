package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Mandalorian extends Enemy {
    public Mandalorian(Cell cell) {
        super(cell);
        health = 10;
        damage = 5;
    }

    public void move(int dx, int dy) {
      // hehe :3
    }

    @Override
    public String getTileName() {
        return "mandalorian";
    }
}
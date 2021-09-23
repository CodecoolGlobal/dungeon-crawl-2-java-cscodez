package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;

public class FallenJedi extends Enemy{

    public FallenJedi(Cell cell) {
        super(cell);
        health = Enemies.FALLEN_JEDI.getHealth();
        damage = Enemies.FALLEN_JEDI.getDamage();
    }

    @Override
    public String getTileName() {
        return Enemies.FALLEN_JEDI.getTileName();
    }
}

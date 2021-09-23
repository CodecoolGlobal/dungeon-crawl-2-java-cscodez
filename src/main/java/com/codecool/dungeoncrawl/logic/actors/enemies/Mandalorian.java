package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Mandalorian extends FlyingEnemy {
    public Mandalorian(Cell cell, Player player) {
        super(cell, player);
        health = Enemies.MANDALORIAN.getHealth();
        damage = Enemies.MANDALORIAN.getDamage();
    }


    @Override
    public String getTileName() {
        return Enemies.MANDALORIAN.getTileName();
    }
}
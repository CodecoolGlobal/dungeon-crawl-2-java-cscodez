package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Weapon extends Item{

    protected int damage;

    public Weapon(Cell cell, String tileName) {
        super(cell, tileName);
    }

    public int getDamage() {
        return damage;
    }
}

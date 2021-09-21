package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class LightSaber extends Item {

    private int damage;

    public LightSaber(Cell cell, int damage, String tileName) {
        super(cell, tileName);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

}
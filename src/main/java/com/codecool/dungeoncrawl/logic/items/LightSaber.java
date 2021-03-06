package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class LightSaber extends Weapon {

    public LightSaber(Cell cell, String tileName) {
        super(cell, tileName);
        this.damage = 40;
        this.name = "Lightsaber";
    }

    public int getDamage() {
        return damage;
    }
}
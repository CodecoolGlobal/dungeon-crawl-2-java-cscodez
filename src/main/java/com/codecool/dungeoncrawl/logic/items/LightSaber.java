package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class LightSaber extends Item {

    private int damage;

    public LightSaber(Cell cell) {
        super(cell);
    }

    public int getDamage() {
        return damage;
    }

}
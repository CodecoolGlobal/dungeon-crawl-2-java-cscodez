package com.codecool.dungeoncrawl.logic.items;

public class LightSaber extends Item{

    private int damage;

    LightSaber(String name, String tileName, int damage) {
        super(name, tileName);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}

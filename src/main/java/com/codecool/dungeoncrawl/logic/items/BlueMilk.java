package com.codecool.dungeoncrawl.logic.items;

public class BlueMilk extends Item{

    private int healthGain;

    BlueMilk(String name, String tileName, int healthGain) {
        super(name, tileName);
        this.healthGain = healthGain;
    }

    public int getHealthGain() {
        return healthGain;
    }
}

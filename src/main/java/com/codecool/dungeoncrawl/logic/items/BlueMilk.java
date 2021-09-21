package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class BlueMilk extends Item{

    private int healthGain;

    BlueMilk(Cell cell) {
        super(cell);
        //this.healthGain = healthGain;
    }

    public int getHealthGain() {
        return healthGain;
    }
}

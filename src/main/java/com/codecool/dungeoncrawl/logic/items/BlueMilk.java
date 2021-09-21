package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class BlueMilk extends Item{
    public BlueMilk(Cell cell, String tileName) {
        super(cell, tileName);
    }
    public int getHealthGain() { return 5; }
}

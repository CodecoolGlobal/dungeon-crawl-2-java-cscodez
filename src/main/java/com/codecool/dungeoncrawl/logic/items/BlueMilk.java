package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class BlueMilk extends Item{
    BlueMilk(Cell cell) {
        super(cell);
    }
    public int getHealthGain() { return 5; }
}

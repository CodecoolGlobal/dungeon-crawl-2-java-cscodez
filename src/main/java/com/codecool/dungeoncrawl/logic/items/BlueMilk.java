package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class BlueMilk extends Item{
    private static final String className = "Blue Milk";
    public BlueMilk(Cell cell, String tileName) {
        super(cell, tileName);
        this.name = className;
    }
    public int getHealthGain() { return 5; }

    public static String getClassName() {
        return className;
    }
}

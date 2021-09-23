package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class BlueMilk extends Item{
    private static final String className = "Blue Milk";
    private static final int healing = 20;
    public BlueMilk(Cell cell, String tileName) {
        super(cell, tileName);
        this.name = className;
    }

    public static String getClassName() {
        return className;
    }

    public static int getHealing() {
        return healing;
    }
}

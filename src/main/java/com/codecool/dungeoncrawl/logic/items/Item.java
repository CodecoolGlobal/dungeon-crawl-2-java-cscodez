package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private String tileName;
    protected String name;

    public Item(Cell cell, String tileName) {
        this.cell = cell;
        this.cell.setItem(this);
        this.tileName = tileName;
    }

    @Override
    public String getTileName() {
        return tileName;
    }

    public String getName() {
        return name;
    }
}








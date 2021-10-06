package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private transient Cell cell;
    private String tileName;
    protected String name;
    private int x;
    private int y;

    public Item(Cell cell, String tileName) {
        this.cell = cell;
        this.cell.setItem(this);
        this.tileName = tileName;
        this.x = cell.getX();
        this.y = cell.getY();
    }

    @Override
    public String getTileName() {
        return tileName;
    }

    public String getName() {
        return name;
    }
}








package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class KeyCard extends Item{
    public KeyCard(Cell cell, String tileName) {
        super(cell, tileName);
        this.name = "Key Card";
    }
}

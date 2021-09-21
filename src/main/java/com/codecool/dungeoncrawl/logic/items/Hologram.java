package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Hologram extends Item{
    public Hologram(Cell cell, String tileName) {
        super(cell, tileName);
    }

    public String getUnknownMessage() {
        return "#>&Ä @ß~ˇ #ä#ä#ä *>;~^>#@{}<";
    }

    public String getTranslatedMessage() {
        return "Our branch naming convention is the best";
    }
}

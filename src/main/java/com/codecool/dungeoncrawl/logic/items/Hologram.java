package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Hologram extends Item{
    Hologram(Cell cell) {
        super(cell);
    }

    public String getUnknownMessage() {
        return "#>&Ä @ß~ˇ #ä#ä#ä *>;~^>#@{}<";
    }

    public String getTranslatedMessage() {
        return "Our branch naming convention is the best";
    }
}

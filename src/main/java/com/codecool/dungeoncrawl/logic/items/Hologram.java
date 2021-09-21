package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Hologram extends Item{
    String unknownMessage = "";
    String translatedMessage = "";
    Hologram(Cell cell) {
        super(cell);
    }

    public String getUnknownMessage() {
        return unknownMessage;
    }

    public String getTranslatedMessage() {
        return translatedMessage;
    }
}

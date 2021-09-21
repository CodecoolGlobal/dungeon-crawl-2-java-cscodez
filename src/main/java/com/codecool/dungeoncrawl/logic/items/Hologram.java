package com.codecool.dungeoncrawl.logic.items;

public class Hologram extends Item{
    String unknownMessage = "";
    String translatedMessage = "";
    Hologram(String name, String tileName) {
        super(name, tileName);
    }

    public String getUnknownMessage() {
        return unknownMessage;
    }

    public String getTranslatedMessage() {
        return translatedMessage;
    }
}

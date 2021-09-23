package com.codecool.dungeoncrawl.logic.actors.enemies;

public enum Enemies {
    STORMTROOPER("stormtrooper", 20, 15),
    GREENBRUTE("greenBrute", 30, 30),
    JAWA("jawa", 10, 0),
    MANDALORIAN("mandalorian", 70, 10),
    FALLEN_JEDI("fallen-jedi", 70, 50);


    private final String tileName;
    private final int health;
    private final int damage;

    Enemies(String tileName, int health, int damage) {
        this.tileName = tileName;
        this.health = health;
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getTileName() {
        return tileName;
    }
}

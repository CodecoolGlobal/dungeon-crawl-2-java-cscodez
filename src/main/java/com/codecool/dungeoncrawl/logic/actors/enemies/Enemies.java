package com.codecool.dungeoncrawl.logic.actors.enemies;

public enum Enemies {
    STORMTROOPER("stormtrooper", 10, 2),
    GREENBRUTE("greenBrute", 20, 10),
    JAWA("jawa", 5, 2),
    MANDALORIAN("mandalorian", 10, 5);


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

package com.codecool.dungeoncrawl.logic.actors.enemies;

public enum Enemies {
    STORMTROOPER(10, 2),
    GREENBRUTE(20, 10),
    JAWA(5, 2),
    MANDALORIAN(10, 5);

    private final int health;
    private final int damage;

    private Enemies(int health, int damage) {
        this.health = health;
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }
}

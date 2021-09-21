package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.LinkedList;

public class Player extends Actor {
    private LinkedList<Item> inventory;

    public Player(Cell cell) {
        super(cell);
        isEnemy = false;
        health = 10;
        damage = 5;
    }

    public String getTileName() {
        return "player";
    }
}

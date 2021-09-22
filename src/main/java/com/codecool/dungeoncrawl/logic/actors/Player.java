package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import javafx.scene.Node;

import java.util.LinkedList;

public class Player extends Actor {
    private final LinkedList<Item> inventory;

    public Player(Cell cell) {
        super(cell);
        isEnemy = false;
        health = 10;
        damage = 5;
        this.inventory = new LinkedList<>();
    }

    public LinkedList<Item> getInventory() {
        return  inventory;
    }


    public void setItemToInventory (Item item) {
        inventory.add(item);
    }

    public boolean isPlayerAlive() {
        return health > 0;
    }

    public String getTileName() {

        return health > 0 ? "player" : "dead-player";
    }
}

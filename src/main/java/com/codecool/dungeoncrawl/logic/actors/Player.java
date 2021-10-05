package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.HashMap;

public class Player extends Actor {
    private final HashMap<String, Integer> inventory;

    public Player(Cell cell) {
        super(cell);
        tileName = "player-down";
        health = 100;
        damage = 10;
        this.inventory = new HashMap<>();
    }

    public HashMap<String, Integer> getInventory() {
        return  inventory;
    }


    public void setItemToInventory (Item item) {
        inventory.merge(item.getName(), 1, Integer::sum);
    }

    public String getName() {return "";}

    public String getTileName() {
        if (getWeapon() != null && getWeapon().getTileName().equals("lightSaber")) {
            return tileName + "-" + getWeapon().getTileName();
        }
        return tileName;
    }

}

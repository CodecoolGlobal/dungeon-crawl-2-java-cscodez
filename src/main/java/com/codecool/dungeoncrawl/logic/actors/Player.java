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


    public void setItemToInventory (String itemName, String increaseOrDecrease) {
        int difference = 0;
        if (increaseOrDecrease.equals("increase")) difference = 1;
        else if (increaseOrDecrease.equals("decrease")) difference = -1;
        inventory.merge(itemName, difference, Integer::sum);
        if (inventory.get(itemName) == 0) inventory.remove(itemName);
    }

    public String getName() {return "";}

    public String getTileName() {
        if (getWeapon() != null && getWeapon().getTileName().equals("lightSaber")) {
            return tileName + "-" + getWeapon().getTileName();
        }
        return tileName;
    }

}

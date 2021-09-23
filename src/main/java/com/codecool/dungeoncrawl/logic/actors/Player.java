package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Player extends Actor {
    private final HashMap<String, Integer> inventory;

    public Player(Cell cell) {
        super(cell);
        tileName = "player-down";
        isEnemy = false;
        health = 10;
        damage = 5;
        this.inventory = new HashMap<>();
    }

    public HashMap<String, Integer> getInventory() {
        return  inventory;
    }


    public void setItemToInventory (Item item) {
        inventory.merge(item.getName(), 1, Integer::sum);
    }

    public String getTileName() {
        if (getWeapon() != null && getWeapon().getTileName().equals("lightSaber")) {
            return tileName + "-" + getWeapon().getTileName();
        }
        return tileName;
    }

    public String inventoryForDisplay() {
        StringBuilder displayString = new StringBuilder();
        for (String item : inventory.keySet()) {
            displayString.append(item);
            displayString.append(" : ");
            displayString.append(inventory.get(item));
            displayString.append("\n");
        }
        return displayString.toString();

    }
}

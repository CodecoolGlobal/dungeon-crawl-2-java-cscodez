package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Door {
    private final transient Cell cell;
    private final int numberOfKeysRequiredToOpen;
    private int x;
    private int y;

    public Door(Cell cell, int numberOfKeysRequiredToOpen) {
        this.cell = cell;
        this.cell.setDoor(this);
        this.numberOfKeysRequiredToOpen = numberOfKeysRequiredToOpen;
        this.x = cell.getX();
        this.y = cell.getY();
    }

    public int getNumberOfKeysRequiredToOpen() {
        return numberOfKeysRequiredToOpen;
    }

    public void checkDoor() {
        boolean isKeyCardPossessed = false;
        for (String item : cell.getGameMap().getPlayer().getInventory().keySet()) {
            if (item.equals("Key Card")) {
                isKeyCardPossessed = true;
                break;
            }
        }
        if (cell.getType().equals(CellType.CLOSED_DOOR) && isKeyCardPossessed) {
            cell.setType(CellType.OPENED_DOOR);
        }
    }
}

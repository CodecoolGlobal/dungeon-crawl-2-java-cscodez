package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Door {
    private final Cell cell;
    private final int numberOfKeysRequiredToOpen;

    public Door(Cell cell, int numberOfKeysRequiredToOpen) {
        this.cell = cell;
        this.cell.setDoor(this);
        this.numberOfKeysRequiredToOpen = numberOfKeysRequiredToOpen;
    }

    public int getNumberOfKeysRequiredToOpen() {
        return numberOfKeysRequiredToOpen;
    }

    public void checkDoor() {
        boolean isKeyCardPossessed = false;
        for (Item item : cell.getGameMap().getPlayer().getInventory()) {
            if (item instanceof KeyCard) {
                isKeyCardPossessed = true;
                break;
            }
        }
        if (cell.getType().equals(CellType.CLOSED_DOOR) && isKeyCardPossessed) {
            cell.setType(CellType.OPENED_DOOR);
        }
    }
}

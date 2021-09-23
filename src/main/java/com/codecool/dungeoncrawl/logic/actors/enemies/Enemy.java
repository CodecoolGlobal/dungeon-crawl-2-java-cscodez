package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Enemy extends Actor {
    private boolean hasMoved;
    public Enemy(Cell cell) {
        super(cell);
        isEnemy = true;
        hasMoved = false;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void swapHasMoved() {
        hasMoved = !hasMoved;
    }
}

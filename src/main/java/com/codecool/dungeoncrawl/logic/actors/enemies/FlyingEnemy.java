package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public abstract class FlyingEnemy extends Enemy {

    public FlyingEnemy(Cell cell) {
        super(cell);
    }


    public void move(Player player) {


        Cell cell = this.getCell();

        int playerX = player.getX();
        int playerY = player.getY();
        int thisX = this.getX();
        int thisY = this.getY();


        int Xdistance = Math.abs(playerX - thisX);
        int Ydistance = Math.abs(playerY - thisY);

        if (Xdistance + Ydistance == 1) {
            attack(player);
            return;
        }

        if (Xdistance > Ydistance) {
            if (playerX > thisX) {
                Cell nextCell = cell.getNeighbor(1, 0);

                checkNextCell(cell, nextCell);
            }
            else {
                Cell nextCell = cell.getNeighbor( -1, 0);
                checkNextCell(cell, nextCell);
            }
        } else {
            if (playerY > getY()) {
                Cell nextCell = cell.getNeighbor(0,  1);
                checkNextCell(cell, nextCell);
            }
            else {
                Cell nextCell = cell.getNeighbor(0, -1);
                checkNextCell(cell, nextCell);
            }
        }




    }

    private void checkNextCell(Cell thisCell, Cell nextCell) {
        if (nextCell.getActor() == null) {
            thisCell.setActor(null);
            this.setCell(nextCell);
            nextCell.setActor(this);
        }
    }
}

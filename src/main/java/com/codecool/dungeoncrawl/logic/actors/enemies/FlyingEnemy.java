package com.codecool.dungeoncrawl.logic.actors.enemies;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class FlyingEnemy extends Enemy {

    public FlyingEnemy(Cell cell) {
        super(cell);
        health = Enemies.FLYINGENEMY.getHealth();
        damage = Enemies.FLYINGENEMY.getDamage();
    }

    @Override
    public String getTileName() {
        return Enemies.FLYINGENEMY.getTileName();
    }

    public void move(int x, int y, Player player) {
        Cell playerPosition = player.getCell();

    }
}

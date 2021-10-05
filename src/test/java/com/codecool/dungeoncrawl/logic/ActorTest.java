package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemies.Jawa;
import com.codecool.dungeoncrawl.logic.actors.enemies.Stormtrooper;
import com.codecool.dungeoncrawl.logic.items.LightSaber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

    @Test
    void moveUpdatesCells() {
        Player player = new Player(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(null, gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveOutOfMap() {
        Player player = new Player(gameMap.getCell(2, 1));
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = new Player(gameMap.getCell(1, 1));
        Stormtrooper stormtrooper = new Stormtrooper(gameMap.getCell(2, 1));
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(2, stormtrooper.getX());
        assertEquals(1, stormtrooper.getY());
        assertEquals(stormtrooper, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void attack_reduceEnemiesHealthByDamage() {
        Player player = new Player(gameMap.getCell(0, 1));
        Jawa jawa = new Jawa(gameMap.getCell(0, 2));
        jawa.setHealth(50);

        player.attack(jawa);
        int jawasNewHealth = jawa.getHealth();

        assertEquals(40, jawasNewHealth);
    }


}
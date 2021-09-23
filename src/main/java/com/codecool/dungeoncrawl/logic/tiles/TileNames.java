package com.codecool.dungeoncrawl.logic.tiles;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.enemies.Enemies;

public enum TileNames {
    EMPTY(CellType.EMPTY.getTileName(), 0, 0),
    WALL(CellType.WALL.getTileName(), 2, 0),
    FLOOR(CellType.FLOOR.getTileName(), 5, 0 ),
    GRAVE(CellType.GRAVE.getTileName(), 9, 1),
    OPENED_DOOR("opened-door", 4, 0),
    CLOSED_DOOR("closed-door", 4, 0),

    JAWA(Enemies.JAWA.getTileName(), 0, 2),
    STORMTROOPER(Enemies.STORMTROOPER.getTileName(), 3,2),
    GREEN_BRUTE(Enemies.GREENBRUTE.getTileName(), 1,2),
    MANDALORIAN(Enemies.MANDALORIAN.getTileName(), 2,2),

    LIGHT_SABER("lightSaber", 0,3),
    BLUE_MILK("blueMilk", 2,3),
    HOLOGRAM("hologram", 3,3),
    KEY_CARD("keyCard", 4,3),
    PLAYER_DOWN("player-down", 1, 1),
    PLAYER_UP("player-up", 2, 1),
    PLAYER_LEFT("player-left", 4, 1),
    PLAYER_RIGHT("player-right", 3, 1),
    PLAYER_DOWN_LIGHTSABER("player-down-lightSaber", 0, 1),
    PLAYER_UP_LIGHTSABER("player-up-lightSaber", 6, 1),
    PLAYER_LEFT_LIGHTSABER("player-left-lightSaber", 8, 1),
    PLAYER_RIGHT_LIGHTSABER("player-right-lightSaber", 7, 1),
    PLAYER_PICK_UP_LIGHTSABER("player-pick-up-lightSaber", 5, 1);


    private final String name;
    private final Tiles.Tile tile;

    TileNames(String name, int i, int j) {
        this.name = name;
        this.tile = new Tiles.Tile(i, j);
    }

    public String getName() {
        return name;
    }

    public Tiles.Tile getTile() {
        return tile;
    }
}


package com.codecool.dungeoncrawl.logic.tiles;

public enum TileNames {
    EMPTY("empty", 0, 0),
    WALL("wall", 2, 0),
    FLOOR("floor", 5, 0 ),
    JAWA("jawa", 0, 2),
    STORMTROOPER("stormtrooper", 3,2),
    GREEN_BRUTE("greenBrute", 1,2),
    LIGHT_SABER("lightSaber", 0,3),
    BLUE_MILK("blueMilk", 2,3),
    HOLOGRAM("hologram", 3,3),
    KEY_CARD("keyCard", 4,3),
    MANDALORIAN("mandalorian", 2,2),
    CLOSED_DOOR("closed-door", 4, 0),
    DEAD("dead", 6, 1),
    OPENED_DOOR("opened-door", 4, 0),
    PLAYER_DOWN("player-down", 1, 1),
    PLAYER_UP("player-up", 2, 1),
    PLAYER_LEFT("player-left", 4, 1),
    PLAYER_RIGHT("player-right", 3, 1),
    PLAYER_DOWN_LIGHTSABER("player-down-lightSaber", 0, 1),
    PLAYER_UP_LIGHTSABER("player-up-lightSaber", 0, 1),
    PLAYER_LEFT_LIGHTSABER("player-left-lightSaber", 0, 1),
    PLAYER_RIGHT_LIGHTSABER("player-right-lightSaber", 0, 1),
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


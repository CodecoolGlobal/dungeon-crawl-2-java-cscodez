package com.codecool.dungeoncrawl.logic.tiles;

public enum TileNames {
    /**
     * map tiles
     */
    EMPTY("empty", 0, 0),
    WALL("wall", 2, 0),
    BLUE_WALL("blue-wall", 7,0),
    BLUE_WALL_PIPE("blue-wall-pipe", 8,0),
    BLUE_WALL_PIPE_2("blue-wall-pipe", 9,0),
    FLOOR("floor", 5, 0 ),
    FLOOR2("floor2", 10, 0),
    CARGO("cargo", 0,4),
    EXIT_1("exit-1", 1,4),
    EXIT_2("exit-2", 2,4),
    EXIT_3("exit-3", 3,4),
    EXIT_4("exit-4", 4,4),
    HYDROCAPSULE_1("hydrocapsule-1",5,4),
    HYDROCAPSULE_2("hydrocapsule-2",6,4),
    SINGLE_PIPE("single-pipe", 7,4),
    PIPE_END("pipe-end",8,4),
    BLUE_WALL_BLOCK("blue-wall-block",9,4),

    /**
     * characters
     */
    JAWA("jawa", 0, 2),
    STORMTROOPER("stormtrooper", 3,2),
    GREEN_BRUTE("greenBrute", 1,2),
    MANDALORIAN("mandalorian", 2,2),
    GREEN_ENEMY("green-enemy", 4,2),
    C3PO("c3po", 5,3),
    /**
     * items
     */
    LIGHT_SABER("lightSaber", 0,3),
    BLUE_MILK("blueMilk", 2,3),
    HOLOGRAM("hologram", 3,3),
    KEY_CARD("keyCard", 4,3),
    CLOSED_DOOR("closed-door", 3, 0),
    OPENED_DOOR("opened-door", 4, 0),
    /**
     * player specific tiles
     */
    DEAD("dead", 9, 1),
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


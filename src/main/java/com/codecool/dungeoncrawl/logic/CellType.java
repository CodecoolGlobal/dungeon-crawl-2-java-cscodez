package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty", false),
    FLOOR("floor", true),
    WALL("wall", false),
    GRAVE("dead", false),
    CLOSED_DOOR("closed-door", true),
    OPENED_DOOR("opened-door", true),
    BLUE_WALL("blue-wall", false),
    BLUE_WALL_PIPE("blue-wall-pipe", false),
    BLUE_WALL_PIPE_2("blue-wall-pipe-2", false),
    FLOOR2("floor2", true),
    CARGO("cargo", false),
    EXIT_1("exit-1", false),
    EXIT_2("exit-2", false),
    EXIT_3("exit-3", false),
    EXIT_4("exit-4", false),
    HYDROCAPSULE_1("hydrocapsule-1", false),
    HYDROCAPSULE_2("hydrocapsule-2", false),
    SINGLE_PIPE("single-pipe", false),
    PIPE_END("pipe-end", false),
    BLUE_WALL_BLOCK("blue-wall-block", false),
    ENTRY_POINT("entry-point", true);

    private final String tileName;
    private final boolean canMoveOn;

    CellType(String tileName, boolean canMoveOn) {
        this.tileName = tileName;
        this.canMoveOn = canMoveOn;
    }

    public String getTileName() {
        return tileName;
    }
    public boolean getCanMoveOn() { return canMoveOn; }
}

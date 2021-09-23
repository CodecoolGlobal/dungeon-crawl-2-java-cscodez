package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    GRAVE("dead"),
    CLOSED_DOOR("closed-door"),
    OPENED_DOOR("opened-door"),
    BLUE_WALL("blue-wall"),
    BLUE_WALL_PIPE("blue-wall-pipe"),
    BLUE_WALL_PIPE_2("blue-wall-pipe-2"),
    FLOOR2("floor2"),
    CARGO("cargo"),
    EXIT_1("exit-1"),
    EXIT_2("exit-2"),
    EXIT_3("exit-3"),
    EXIT_4("exit-4"),
    HYDROCAPSULE_1("hydrocapsule-1"),
    HYDROCAPSULE_2("hydrocapsule-2"),
    SINGLE_PIPE("single-pipe"),
    PIPE_END("pipe-end"),
    BLUE_WALL_BLOCK("blue-wall-block");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

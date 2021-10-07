package com.codecool.dungeoncrawl.dao.player;

public enum PlayerColumns {
    ID("id"),
    PLAYER_NAME("player_name"),
    HP("hp"),
    X("x"),
    Y("y"),
    DAMAGE("damage"),
    TILE_NAME("tile_name");

    private final String name;

    PlayerColumns(String playerColumnName) {
        this.name = playerColumnName;
    }

    public String getName() {
        return name;
    }
}


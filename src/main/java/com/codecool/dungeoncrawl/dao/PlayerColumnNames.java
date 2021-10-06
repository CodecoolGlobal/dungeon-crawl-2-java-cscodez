package com.codecool.dungeoncrawl.dao;

public enum PlayerColumnNames {
    ID("id"),
    PLAYER_NAME("player_name"),
    HP("hp"),
    X("x"),
    Y("y"),
    DAMAGE("damage"),
    TILE_NAME("tile_name");

    private final String playerColumnName;

    PlayerColumnNames(String playerColumnName) {
        this.playerColumnName = playerColumnName;
    }

    public String getPlayerColumnName() {
        return playerColumnName;
    }
}


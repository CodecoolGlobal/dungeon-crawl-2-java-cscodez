package com.codecool.dungeoncrawl.dao;

public enum GameStateColumnNames {
    ID("id"),
    CURRENT_MAP("current_map"),
    SAVED_AT("saved_at"),
    PLAYER_ID("player_id"),
    NAME_OF_SAVE("name_of_save");


    private final String GameStateColumnName;

    GameStateColumnNames(String gameStateColumnName) {
        GameStateColumnName = gameStateColumnName;
    }

    public String getGameStateColumnName() {
        return GameStateColumnName;
    }
}

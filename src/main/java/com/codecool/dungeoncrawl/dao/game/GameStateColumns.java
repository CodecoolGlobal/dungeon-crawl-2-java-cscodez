package com.codecool.dungeoncrawl.dao.game;

public enum GameStateColumns {
    ID("id"),
    CURRENT_MAP("current_map"),
    SAVED_AT("saved_at"),
    PLAYER_ID("player_id"),
    NAME_OF_SAVE("name_of_save");


    private final String name;

    GameStateColumns(String gameStateColumnName) {
        name = gameStateColumnName;
    }

    public String getName() {
        return name;
    }
}

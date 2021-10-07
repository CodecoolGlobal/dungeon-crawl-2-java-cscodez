package com.codecool.dungeoncrawl.dao.game;

import com.codecool.dungeoncrawl.model.GameState;

import java.util.HashMap;
import java.util.List;

public interface GameStateDao {
    void add(GameState state);
    void update(GameState state);
    GameState get(int id);
    HashMap<Integer, String> getIdAndName();
}

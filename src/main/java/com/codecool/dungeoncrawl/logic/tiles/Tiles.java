package com.codecool.dungeoncrawl.logic.tiles;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles_sw.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put(TileNames.EMPTY.getName(), TileNames.EMPTY.getTile());
        tileMap.put(TileNames.WALL.getName(), TileNames.WALL.getTile());
        tileMap.put(TileNames.FLOOR.getName(), TileNames.FLOOR.getTile());
        tileMap.put(TileNames.JAWA.getName(), TileNames.JAWA.getTile());
        tileMap.put(TileNames.STORMTROOPER.getName(), TileNames.STORMTROOPER.getTile());
        tileMap.put(TileNames.GREEN_BRUTE.getName(), TileNames.GREEN_BRUTE.getTile());
        tileMap.put(TileNames.LIGHT_SABER.getName(), TileNames.LIGHT_SABER.getTile());
        tileMap.put(TileNames.BLUE_MILK.getName(), TileNames.BLUE_MILK.getTile());
        tileMap.put(TileNames.HOLOGRAM.getName(), TileNames.HOLOGRAM.getTile());
        tileMap.put(TileNames.KEY_CARD.getName(), TileNames.KEY_CARD.getTile());
        tileMap.put(TileNames.MANDALORIAN.getName(), TileNames.MANDALORIAN.getTile());
        tileMap.put(TileNames.CLOSED_DOOR.getName(), TileNames.CLOSED_DOOR.getTile());
        tileMap.put(TileNames.GRAVE.getName(), TileNames.GRAVE.getTile());
        tileMap.put(TileNames.OPENED_DOOR.getName(), TileNames.OPENED_DOOR.getTile());
        tileMap.put(TileNames.PLAYER_DOWN.getName(), TileNames.PLAYER_DOWN.getTile());
        tileMap.put(TileNames.PLAYER_UP.getName(), TileNames.PLAYER_UP.getTile());
        tileMap.put(TileNames.PLAYER_LEFT.getName(), TileNames.PLAYER_LEFT.getTile());
        tileMap.put(TileNames.PLAYER_RIGHT.getName(), TileNames.PLAYER_RIGHT.getTile());
        tileMap.put(TileNames.PLAYER_DOWN_LIGHTSABER.getName(), TileNames.PLAYER_DOWN_LIGHTSABER.getTile());
        tileMap.put(TileNames.PLAYER_UP_LIGHTSABER.getName(), TileNames.PLAYER_UP_LIGHTSABER.getTile());
        tileMap.put(TileNames.PLAYER_LEFT_LIGHTSABER.getName(), TileNames.PLAYER_LEFT_LIGHTSABER.getTile());
        tileMap.put(TileNames.PLAYER_RIGHT_LIGHTSABER.getName(), TileNames.PLAYER_RIGHT_LIGHTSABER.getTile());
        tileMap.put(TileNames.PLAYER_PICK_UP_LIGHTSABER.getName(), TileNames.PLAYER_PICK_UP_LIGHTSABER.getTile());
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}

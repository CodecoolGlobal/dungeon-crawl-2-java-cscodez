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
        tileMap.put(TileNames.FLOOR2.getName(), TileNames.FLOOR2.getTile());
        tileMap.put(TileNames.JAWA.getName(), TileNames.JAWA.getTile());
        tileMap.put(TileNames.STORMTROOPER.getName(), TileNames.STORMTROOPER.getTile());
        tileMap.put(TileNames.GREEN_BRUTE.getName(), TileNames.GREEN_BRUTE.getTile());
        tileMap.put(TileNames.LIGHT_SABER.getName(), TileNames.LIGHT_SABER.getTile());
        tileMap.put(TileNames.BLUE_MILK.getName(), TileNames.BLUE_MILK.getTile());
        tileMap.put(TileNames.HOLOGRAM.getName(), TileNames.HOLOGRAM.getTile());
        tileMap.put(TileNames.KEY_CARD.getName(), TileNames.KEY_CARD.getTile());
        tileMap.put(TileNames.MANDALORIAN.getName(), TileNames.MANDALORIAN.getTile());
        tileMap.put(TileNames.CLOSED_DOOR.getName(), TileNames.CLOSED_DOOR.getTile());
        tileMap.put(TileNames.DEAD.getName(), TileNames.DEAD.getTile());
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
        tileMap.put(TileNames.BLUE_WALL.getName(), TileNames.BLUE_WALL.getTile());
        tileMap.put(TileNames.BLUE_WALL_PIPE.getName(), TileNames.BLUE_WALL_PIPE.getTile());
        tileMap.put(TileNames.BLUE_WALL_PIPE_2.getName(), TileNames.BLUE_WALL_PIPE_2.getTile());
        tileMap.put(TileNames.BLUE_WALL_BLOCK.getName(), TileNames.BLUE_WALL_BLOCK.getTile());
        tileMap.put(TileNames.CARGO.getName(), TileNames.CARGO.getTile());
        tileMap.put(TileNames.EXIT_1.getName(), TileNames.EXIT_1.getTile());
        tileMap.put(TileNames.EXIT_2.getName(), TileNames.EXIT_2.getTile());
        tileMap.put(TileNames.EXIT_3.getName(), TileNames.EXIT_3.getTile());
        tileMap.put(TileNames.EXIT_4.getName(), TileNames.EXIT_4.getTile());
        tileMap.put(TileNames.HYDROCAPSULE_1.getName(), TileNames.HYDROCAPSULE_1.getTile());
        tileMap.put(TileNames.HYDROCAPSULE_2.getName(), TileNames.HYDROCAPSULE_2.getTile());
        tileMap.put(TileNames.SINGLE_PIPE.getName(), TileNames.SINGLE_PIPE.getTile());
        tileMap.put(TileNames.PIPE_END.getName(), TileNames.PIPE_END.getTile());
        tileMap.put(TileNames.GREEN_ENEMY.getName(), TileNames.GREEN_ENEMY.getTile());
        tileMap.put(TileNames.C3PO.getName(), TileNames.C3PO.getTile());
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}

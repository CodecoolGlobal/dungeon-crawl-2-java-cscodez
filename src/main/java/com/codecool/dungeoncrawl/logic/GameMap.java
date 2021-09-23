package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemies.Enemy;
import com.codecool.dungeoncrawl.util.Directions;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void moveEnemies() {

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Actor enemy = cell.getActor();
                if (enemy instanceof Enemy) {

                    if (!((Enemy) enemy).getHasMoved()) {
                        Directions direction = Directions.getRandomDirection();
                        System.out.println(direction);
                        enemy.move(direction.getX(), direction.getY());
                        ((Enemy) enemy).swapHasMoved();
                    } else {
                        ((Enemy) enemy).swapHasMoved();
                    }

                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getCells() {
        return cells;
    }
}

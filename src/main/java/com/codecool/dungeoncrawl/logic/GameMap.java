package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemies.Enemy;
import com.codecool.dungeoncrawl.logic.actors.enemies.FlyingEnemy;
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
                Actor actor = cell.getActor();
                if (actor instanceof Enemy) {
                    Enemy enemy = (Enemy)actor;
                    if (!enemy.getHasMoved()) {

                        if (enemy instanceof FlyingEnemy) {
                            enemy.move(player);
                        } else {
                            Directions direction = Directions.getRandomDirection();
                            enemy.move(direction.getX(), direction.getY());
                        }

                        enemy.swapHasMoved();
                    } else {
                        enemy.swapHasMoved();
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

    public int[] getPlayerCoordinate(int visibleSize){
        int playerX = player.getX();
        int playerY = player.getY();

        int size = (visibleSize -1 )/ 2;

        int startX = playerX - size;
        int startY = playerY - size;

        if(startX < 0){
            startX = 0;
        }else if(playerX + size >= width - 1) {
            startX = width - visibleSize;
        }
        if(startY < 0){
            startY = 0;
        }else if(playerY + size >= height - 1){
            startY = height - visibleSize;
        }
        return new int[]{startX, startY};
    }
}

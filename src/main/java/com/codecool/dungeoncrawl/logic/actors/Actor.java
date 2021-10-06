package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.actors.enemies.Enemy;
import com.codecool.dungeoncrawl.logic.items.Weapon;

public abstract class Actor implements Drawable {
    protected String tileName;
    private transient Cell cell;
    protected int health = 10;
    protected int damage;
    private Weapon weapon;
    private int x;
    private int y;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
        this.x=cell.getX();
        this.y=cell.getY();
    }

    public void attack(Actor enemy) {

        enemy.setHealth(enemy.getHealth() - getDamage());
        if (enemy.getHealth() <= 0) {
            enemy.getCell().setActor(null);
        } else {
            health -= enemy.getDamage();
        }

    }

    public void setAttackMode(Cell nextCell) {
        boolean playerMoves = this instanceof Player;

        if (playerMoves) {
            if (nextCell.getActor() instanceof Enemy) {
                attack(nextCell.getActor());
            }
        } else if (this instanceof Enemy && nextCell.getActor() instanceof Player) {
            attack(nextCell.getActor());
        }
    }


    public void move(Player player) {

    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.canMoveOnCell() && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;

        } else if (nextCell.getActor() != null) {
            setAttackMode(nextCell);
        }
        if (cell.getDoor() != null) cell.getDoor().checkDoor();
    }

    public int getDamage() {
        if (weapon != null) {
            return damage + weapon.getDamage();
        }
        return damage;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String getTileName() {
        return tileName;
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    public void setCell(Cell cell){
        this.cell = cell;
        this.x = cell.getX();
        this.y = cell.getY();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }
}
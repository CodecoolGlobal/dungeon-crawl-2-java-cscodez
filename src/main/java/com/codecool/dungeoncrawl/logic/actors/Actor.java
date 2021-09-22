package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Weapon;

public abstract class Actor implements Drawable {
    protected String tileName;
    private Cell cell;
    protected int health = 10;
    protected boolean isEnemy;
    protected int damage;
    private Weapon weapon;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
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
            if (nextCell.getActor().isEnemy()) {
                attack(nextCell.getActor());
            }
        } else if (this.isEnemy && nextCell.getActor() instanceof Player) {
            attack(nextCell.getActor());
        }
    }


    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!isWall(nextCell) && !isActor(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;

        } else if (isActor(nextCell)) {
            setAttackMode(nextCell);
        }
        if (cell.getDoor() != null) cell.getDoor().checkDoor();
    }

    private boolean isWall(Cell cell) {
        return cell.getTileName().equals("wall");
    }

    private boolean isActor(Cell cell) {
        return cell.getActor() != null;
    }

    private boolean isEnemy() {
        return this.isEnemy;
    }

    public int getDamage() {
        if (weapon != null) {
            System.out.println(damage + weapon.getDamage());
            return damage + weapon.getDamage();
        }
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
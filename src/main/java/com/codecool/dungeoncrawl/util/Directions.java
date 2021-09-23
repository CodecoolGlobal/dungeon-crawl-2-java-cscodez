package com.codecool.dungeoncrawl.util;

public enum Directions {
    NORTH(-1,0), EAST(0,1), SOUTH(1,0), WEST(0,-1);
    private final int x;
    private final int y;

    Directions(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Directions getRandomDirection() {

        int randomIndex = Randomizer.nextInt(values().length);

        return values()[randomIndex];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

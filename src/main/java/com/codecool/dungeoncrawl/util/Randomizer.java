package com.codecool.dungeoncrawl.util;

import java.util.Random;

public class Randomizer {
    static Random random = new Random();

    public static int nextInt(int number) {
        return random.nextInt(number);
    }
}

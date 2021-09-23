package com.codecool.dungeoncrawl.util;

import java.util.HashMap;

public class BuildUI {
    public static String inventoryForDisplay(HashMap<String, Integer> hashMap) {
        StringBuilder displayString = new StringBuilder();
        for (String item : hashMap.keySet()) {
            displayString.append(item);
            displayString.append(" : ");
            displayString.append(hashMap.get(item));
            displayString.append("\n");
        }
        return displayString.toString();

    }
}

package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class BuildUI {
    public String inventoryForDisplay(HashMap<String, Integer> hashMap) {
        StringBuilder displayString = new StringBuilder();
        for (String item : hashMap.keySet()) {
            displayString.append(item);
            displayString.append(" : ");
            displayString.append(hashMap.get(item));
            displayString.append("\n");
        }
        return displayString.toString();

    }

    public void buttonHandler(GridPane ui, Cell cell, GameMap map, Label inventoryLabel) {
        Button yesButton = new Button("Pick up\n" + cell.getItem().getName());
        Button noButton = new Button("Don't pickup\n" + cell.getItem().getName());
        EventHandler<ActionEvent> yesEvent = e -> {
            map.getPlayer().setItemToInventory(cell.getItem());

            if (cell.getItem() instanceof Weapon) {
                map.getPlayer().setWeapon((Weapon) cell.getItem());
            }

            cell.setItem(null);
            ui.getChildren().remove(yesButton);
            ui.getChildren().remove(noButton);
            inventoryLabel.setText(this.inventoryForDisplay(map.getPlayer().getInventory()));

        };
        EventHandler<ActionEvent> noEvent = e -> {
            System.out.println("helloNo");
            ui.getChildren().remove(yesButton);
            ui.getChildren().remove(noButton);
        };

        yesButton.setOnAction(yesEvent);
        noButton.setOnAction(noEvent);
        ui.add(yesButton, 0, 1);
        ui.add(noButton, 1, 1);

    }
}

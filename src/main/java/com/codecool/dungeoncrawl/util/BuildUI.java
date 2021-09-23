package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.BlueMilk;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import com.codecool.dungeoncrawl.logic.tiles.TileNames;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class BuildUI {

    public void inventoryDisplayer(GridPane ui, GameMap map, Label healthLabel) {
        int itemCol = 0;
        int buttonCol = 1;
        int row = 3;
        Player player = map.getPlayer();
        HashMap<String, Integer> hashMap= player.getInventory();
        HashMap<Label, Button> whatsOnUi= new HashMap<>();
        for (String item : hashMap.keySet()) {
            if (!item.equals("Lightsaber")) {
                StringBuilder displayString = new StringBuilder();
                Label inventoryLabel = new Label();
                Button useButton = new Button("use");
                displayString.append(item);
                displayString.append(" : ");
                displayString.append(hashMap.get(item));
                displayString.append("\n");
                inventoryLabel.setText(displayString.toString());
                ui.add(inventoryLabel, itemCol, row);
                ui.add(useButton, buttonCol, row);
                whatsOnUi.put(inventoryLabel, useButton);
                row++;
                if (item.equals(BlueMilk.getClassName())) {
                    EventHandler<ActionEvent> useButtonEvent = e -> {
                        player.setHealth(player.getHealth()+BlueMilk.getHealing());
                        healthLabel.setText("" + map.getPlayer().getHealth());
                        ui.getChildren().remove(inventoryLabel);
                        ui.getChildren().remove(useButton);

                    };
                    useButton.setOnAction(useButtonEvent);
                }
            }
        }

        Button exitButton = new Button("exit");
        ui.add(exitButton, 0, row);
        EventHandler<ActionEvent> exitHandler = e -> {
            for (Label label:whatsOnUi.keySet()) {
                ui.getChildren().remove(label);
                ui.getChildren().remove(whatsOnUi.get(label));
            }
            ui.getChildren().remove(exitButton);
        };
        exitButton.setOnAction(exitHandler);

    }

    public void pickUpButtonHandler(GridPane ui, Cell cell, GameMap map) {
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

    public void itemButtonHandler(GridPane ui, Cell cell, GameMap map) {
        Button useButton = new Button("use");
        EventHandler<ActionEvent> useEvent = e -> {
            System.out.println("hello");
        };
        useButton.setOnAction(useEvent);
        //ui.add();
    }
}

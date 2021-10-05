package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.BlueMilk;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuildUI {

    GameDatabaseManager manager = new GameDatabaseManager();
    HashMap<Label, Button> whatsOnUi;
    GridPane ui;
    Label inventoryLabel;

    public void inventoryDisplayer(GridPane ui, GameMap map, Label healthLabel) {
        int itemCol = 0;
        int buttonCol = 1;
        int row = 3;
        this.ui = ui;
        //Label inventoryLabel = new Label();
        Player player = map.getPlayer();
        HashMap<String, Integer> hashMap= player.getInventory();
        this.whatsOnUi= new HashMap<>();
        if (hashMap.keySet().size() == 0) {
            this.inventoryLabel = new Label();
            inventoryLabel.setText("Inventory\nis empty!");
            this.ui.add(inventoryLabel, 0, 1);
            this.whatsOnUi.put(inventoryLabel, null);
        }
        for (String item : hashMap.keySet()) {
            if (!item.equals("Lightsaber")) {
                StringBuilder displayString = new StringBuilder();
                Button useButton = new Button("use");
                this.inventoryLabel = new Label();
                displayString.append(item);
                displayString.append(" : ");
                displayString.append(hashMap.get(item));
                displayString.append("\n");
                inventoryLabel.setText(displayString.toString());
                this.ui.add(inventoryLabel, itemCol, row);
                this.ui.add(useButton, buttonCol, row);
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
    }

    public void removeInventory() {
        for (Label label:this.whatsOnUi.keySet()) {
            this.ui.getChildren().remove(label);
            this.ui.getChildren().remove(whatsOnUi.get(label));
        }
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

    public void savingMenu(GridPane ui, GameMap map) {
        Button cancelButton = new Button("Cancel");
        Button saveAndExitButton = new Button("Save & Exit");
        Button exitWithoutSaveButton = new Button("Exit");

        ArrayList<Button> saveMenuButtons = new ArrayList<>();

        ui.add(saveAndExitButton, 0, 1);
        saveMenuButtons.add(saveAndExitButton);
        ui.add(exitWithoutSaveButton, 0, 2);
        saveMenuButtons.add(exitWithoutSaveButton);
        ui.add(cancelButton, 0, 4);
        saveMenuButtons.add(cancelButton);

        EventHandler<ActionEvent> saveAndExitButtonHandler = e -> {
            //manager.savePlayer(map.getPlayer());
            System.exit(0);

        };

        EventHandler<ActionEvent> exitButtonHandler = e -> {
            System.exit(0);
        };

        EventHandler<ActionEvent> cancelHandler = e -> {
            for (Button button : saveMenuButtons) {
                ui.getChildren().remove(button);
            }
        };
        cancelButton.setOnAction(cancelHandler);
        saveAndExitButton.setOnAction(saveAndExitButtonHandler);
        exitWithoutSaveButton.setOnAction(exitButtonHandler);
    }
}

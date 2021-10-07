package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.BlueMilk;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuildUI {

    GameDatabaseManager manager = new GameDatabaseManager();
    HashMap<Label, Button> whatsOnUi;
    ArrayList<Button> saveMenuButtons = new ArrayList<>();
    GridPane ui;
    Label inventoryLabel;

    public void inventoryDisplayer(GridPane ui, GameMap map, Label healthLabel) {
        int itemCol = 0;
        int buttonCol = 1;
        int row = 3;
        this.ui = ui;
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

    public void savingMenu(GameMap map, GridPane ui ) {
        Label saved = new Label();
        saved.setText("Saved");

        Button backButton = new Button("Back");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");

        ui.add(saveButton, 0, 1);
        saveMenuButtons.add(saveButton);
        ui.add(exitButton, 0, 2);
        saveMenuButtons.add(exitButton);
        ui.add(backButton, 0, 4);
        saveMenuButtons.add(backButton);

        EventHandler<ActionEvent> saveAndExitButtonHandler = e -> {
            try {
                manager.setup();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            textInputPopUp(ui, map, saveButton, saved);
        };

        EventHandler<ActionEvent> exitButtonHandler = e -> System.exit(0);

        EventHandler<ActionEvent> backHandler = e -> {
            for (Button button : saveMenuButtons) {
                ui.getChildren().remove(button);
                ui.getChildren().remove(saved);
            }
        };
        backButton.setOnAction(backHandler);
        saveButton.setOnAction(saveAndExitButtonHandler);
        exitButton.setOnAction(exitButtonHandler);
    }

    public void removeSavingMenu(GridPane ui) {
        for (Button button : saveMenuButtons) {
            ui.getChildren().remove(button);
        }
        saveMenuButtons = new ArrayList<>();
    }

    public void textInputPopUp(GridPane ui, GameMap map, Button saveAndExitButton, Label saved) {
        TextField saveNameInput = new TextField();
        Button saveButton = new Button("Save");

        ui.getChildren().remove(saveAndExitButton);

        ui.add(saveNameInput,0, 1 );
        ui.add(saveButton, 1, 1);
        saveMenuButtons.add(saveButton);

        EventHandler<ActionEvent> saveButtonHandler = e -> {
            ui.getChildren().remove(saveNameInput);
            ui.getChildren().remove(saveButton);
            ui.add(saved, 0, 1);
            PlayerModel playerModel = manager.savePlayer(map.getPlayer());
            String gameMap = Serialization.serialize(map);
            System.out.println(gameMap);
            manager.saveGameState(manager.makeGameState(playerModel, saveNameInput.getText(), gameMap));

        };

        saveButton.setOnAction(saveButtonHandler);
    }

    public void loadMenu(GridPane ui, GameMap map) {
        Button startButton = new Button("Start new game");
        Button loadButton = new Button("Load");
        Button exitButton = new Button("Exit");

        ui.add(loadButton, 0, 1);
        //saveMenuButtons.add(loadButton);
        ui.add(exitButton, 0, 2);
        //saveMenuButtons.add(exitButton);
        ui.add(startButton, 0, 4);
        //saveMenuButtons.add(startButton);

        EventHandler<ActionEvent> loadButtonHandler = e -> {
            try {
                manager.setup();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            loadInputPopUp(ui, loadButton, startButton, exitButton, map);
        };
        loadButton.setOnAction(loadButtonHandler);



    }

    public void loadInputPopUp(GridPane ui, Button loadButton, Button startButton, Button exitButton, GameMap map) {
        Label l = new Label("Choose a saved game: ");
        Button loadChosenGame = new Button("Load");

        HashMap<Integer, String> allIdsAndNames = manager.getAllIdAndName();

        ChoiceBox<String> choices = new ChoiceBox<>(FXCollections.observableArrayList(allIdsAndNames.values()));

        //getChoice(choices);

        ui.getChildren().remove(startButton);
        ui.add(choices, 0, 1);
        ui.add(loadChosenGame, 1, 1);

        EventHandler<ActionEvent> loadSavedGameHandler = e -> {
            String choseSavedGameName = choices.getSelectionModel().getSelectedItem();
            System.out.println(choseSavedGameName);
            for (var item : allIdsAndNames.entrySet()) {
                if(item.getValue().equals(choseSavedGameName)) {
                    Main.map = manager.getSavedGameState(item.getKey());
                    buildConnectionsAfterLoad(Main.map);
                }
            }

            ui.getChildren().remove(choices);
            ui.getChildren().remove(loadChosenGame);
            ui.getChildren().remove(loadButton);
            ui.getChildren().remove(exitButton);

        };
        loadChosenGame.setOnAction(loadSavedGameHandler);

    }

    private void buildConnectionsAfterLoad(GameMap gameMap) {
        for (Cell[] cell : gameMap.getCells()) {
            for (Cell cell1 : cell) {
                cell1.setGameMap(gameMap);
                if (cell1.getActor() != null) cell1.getActor().setCell(cell1);
                if (cell1.getActor() != null && cell1.getActor() instanceof Player) gameMap.setPlayer((Player) cell1.getActor());
                if (cell1.getItem() != null) cell1.getItem().setCell(cell1);
            }
        }
    }
}

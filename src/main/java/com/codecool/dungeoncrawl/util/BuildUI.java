package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.BlueMilk;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import com.codecool.dungeoncrawl.logic.tiles.Tiles;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BuildUI {

    private final GameDatabaseManager manager = new GameDatabaseManager();
    private HashMap<Label, Button> whatsOnUi;
    private ArrayList<Button> saveMenuButtons = new ArrayList<>();
    private GridPane ui;
    private Label inventoryLabel;
    private final int visibleSize = 20;
    private final Canvas canvas = new Canvas(
            visibleSize * Tiles.TILE_WIDTH,
            visibleSize * Tiles.TILE_WIDTH);
    private final GraphicsContext context = canvas.getGraphicsContext2D();
    private final Label healthLabel = new Label();



    public BuildUI(GridPane ui) {
        this.ui = ui;
        Label forHealth = new Label("Health:");

        forHealth.setStyle("-fx-font-weight: bold");
        ui.add(forHealth, 0, 0);
        ui.add(healthLabel, 1, 0);
        healthLabel.setTextFill(Color.RED);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Label getHealthLabel() {
        return healthLabel;
    }

    public void inventoryDisplayer(GridPane ui, GameMap map, Label healthLabel) {
        int itemCol = 0;
        int buttonCol = 1;
        int row = 3;
        this.ui = ui;
        Player player = map.getPlayer();
        HashMap<String, Integer> hashMap= player.getInventory();
        hashMap.remove("Lightsaber");
        this.whatsOnUi= new HashMap<>();
        if (hashMap.keySet().size() == 0) showEmptyInventory();
        else for (String item : hashMap.keySet()) {
                row = buildRow(ui, healthLabel, itemCol, buttonCol, row, player, hashMap, item);
        }
    }

    private int buildRow(GridPane ui, Label healthLabel, int itemCol, int buttonCol, int row, Player player, HashMap<String, Integer> hashMap, String item) {
        Button useButton = new Button("use");
        StringBuilder displayString = getInventoryLine(hashMap, item);
        inventoryLabel.setText(displayString.toString());
        this.ui.add(inventoryLabel, itemCol, row);
        this.ui.add(useButton, buttonCol, row);
        whatsOnUi.put(inventoryLabel, useButton);
        row++;
        if (item.equals(BlueMilk.getClassName())) {
            EventHandler<ActionEvent> useButtonEvent = e -> {
                useBlueMilk(ui, healthLabel, player, hashMap, item, useButton);
            };
            useButton.setOnAction(useButtonEvent);
        }
        return row;
    }

    private void showEmptyInventory() {
        this.inventoryLabel = new Label();
        inventoryLabel.setText("Inventory\nis empty!");
        this.ui.add(inventoryLabel, 0, 1);
        this.whatsOnUi.put(inventoryLabel, null);
    }

    private void useBlueMilk(GridPane ui, Label healthLabel, Player player, HashMap<String, Integer> hashMap, String item, Button useButton) {
        if (hashMap.get(item) > 0) {
            player.setHealth(player.getHealth() + BlueMilk.getHealing());
            Main.map.getPlayer().setItemToInventory(item, "decrease");
            clearSidePanel(ui);
            inventoryDisplayer(ui, Main.map, healthLabel);
        }
        healthLabel.setText("" + Main.map.getPlayer().getHealth());
    }

    public void clearSidePanel(GridPane ui) {
        List<Node> nodesToKeep= new LinkedList<>();
        nodesToKeep.add(ui.getChildren().get(0));
        nodesToKeep.add(ui.getChildren().get(1));
        ui.getChildren().clear();
        for (Node node : nodesToKeep) {
            ui.getChildren().add(node);
        }
    }

    private StringBuilder getInventoryLine(HashMap<String, Integer> hashMap, String item) {
        StringBuilder displayString = new StringBuilder();
        this.inventoryLabel = new Label();
        displayString.append(item);
        displayString.append(" : ");
        displayString.append(hashMap.get(item));
        displayString.append("\n");
        return displayString;
    }

    public void pickUpButtonHandler(GridPane ui, Cell cell, GameMap map) {
        Button yesButton = new Button("Pick up\n" + cell.getItem().getName());
        Button noButton = new Button("Don't pickup\n" + cell.getItem().getName());
        EventHandler<ActionEvent> yesEvent = e -> {
            map.getPlayer().setItemToInventory(cell.getItem().getName(), "increase");

            if (cell.getItem() instanceof Weapon) {
                map.getPlayer().setWeapon((Weapon) cell.getItem());
            }
            cell.setItem(null);
            ui.getChildren().remove(yesButton);
            ui.getChildren().remove(noButton);
        };
        EventHandler<ActionEvent> noEvent = e -> {
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
        Button exitButton = new Button("Exit  ");

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

        EventHandler<ActionEvent> exitButtonHandler = e -> exit();

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

    private void exit() {
        System.exit(0);
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
        Button startButton = new Button("New game");
        Button loadButton = new Button("Load  ");
        Button exitButton = new Button("Exit   ");

        ui.add(loadButton, 0, 1);
        ui.add(exitButton, 0, 2);
        ui.add(startButton, 0, 4);

        EventHandler<ActionEvent> loadButtonHandler = e -> {
            try {
                manager.setup();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            loadInputPopUp(ui, loadButton, startButton, exitButton, map);
        };

        EventHandler<ActionEvent> startNewGameHandler = e -> {
            ui.getChildren().remove(startButton);
            ui.getChildren().remove(loadButton);
            ui.getChildren().remove(exitButton);
        };

        EventHandler<ActionEvent> exitButtonHandler = e -> exit();

        loadButton.setOnAction(loadButtonHandler);
        startButton.setOnAction(startNewGameHandler);
        exitButton.setOnAction(exitButtonHandler);



    }

    public void loadInputPopUp(GridPane ui, Button loadButton, Button startButton, Button exitButton, GameMap map) {
        Label l = new Label("Choose a saved game: ");
        Button loadChosenGame = new Button("Load");

        HashMap<Integer, String> allIdsAndNames = manager.getAllIdAndName();

        ChoiceBox<String> choices = new ChoiceBox<>(FXCollections.observableArrayList(allIdsAndNames.values()));

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
                    refresh();
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
                if (cell1.getDoor() != null) cell1.getDoor().setCell(cell1);
            }
        }
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int[] startCoordinate = Main.map.getPlayerCoordinate(visibleSize);

        int j = 0;
        int k = 0;

        for (int x = startCoordinate[0]; x < visibleSize + startCoordinate[0]; x++) {
            for (int y = startCoordinate[1]; y < startCoordinate[1] + visibleSize; y++) {
                Cell cell = Main.map.getCell(x, y);
                if (cell.getActor() != null) {
                    if (cell.getItem() != null && cell.getActor() instanceof Player) {
                        pickUpButtonHandler(this.ui, cell, Main.map);
                    }
                    Tiles.drawTile(context, cell.getActor(), k, j);

                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), k, j);
                } else {
                    Tiles.drawTile(context, cell, k, j);
                }
                j++;
            }
            j = 0;
            k++;
        }
        healthLabel.setText("" + Main.map.getPlayer().getHealth());
        healthLabel.setStyle("-fx-font-size: 20px");
        healthLabel.setStyle("-fx-font-weight: bold");

        if (Integer.parseInt(healthLabel.getText()) < 20) {
            healthLabel.setTextFill(Color.RED);
        } else {
            healthLabel.setTextFill(Color.web("#0000FF"));
        }
        if (checkIfDoorIsOpen()) {
            Player player = Main.map.getPlayer();
            renderNewMap(player);
        }
    }

    private boolean checkIfDoorIsOpen() {
        for (Cell[] cellRow : Main.map.getCells()) {
            for (Cell cell : cellRow) {
                if (cell.getType().equals(CellType.OPENED_DOOR)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void renderNewMap(Player player) {
        Main.map = MapLoader.loadMap("/map2.txt");
        Cell playerCell = Main.map.getPlayer().getCell();
        player.setCell(playerCell);
        Main.map.setPlayer(player);
    }
}

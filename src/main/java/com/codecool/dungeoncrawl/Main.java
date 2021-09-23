package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Weapon;
import com.codecool.dungeoncrawl.logic.tiles.Tiles;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    GridPane ui = new GridPane();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.ui.setPrefWidth(250);
        this.ui.setPadding(new Insets(10));

        this.ui.add(new Label("Health: "), 0, 0);
        this.ui.add(healthLabel, 1, 0);


        this.ui.add(inventoryLabel, 0, 3);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (map.getPlayer().isAlive()) {
            switch (keyEvent.getCode()) {
                case UP:
                    map.getPlayer().setTileName("player-up");
                    map.getPlayer().move(0, -1);
                    break;
                case DOWN:
                    map.getPlayer().setTileName("player-down");
                    map.getPlayer().move(0, 1);
                    break;
                case LEFT:
                    map.getPlayer().setTileName("player-left");
                    map.getPlayer().move(-1, 0);
                    break;
                case RIGHT:
                    map.getPlayer().setTileName("player-right");
                    map.getPlayer().move(1, 0);
                    break;
            }
        }
        map.moveEnemies();
        if (!map.getPlayer().isAlive()) {
            map.getPlayer().setTileName("dead");
        }
        refresh();
    }


    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null ) {
                    if (cell.getItem() != null && cell.getActor() instanceof Player) {
                        if (cell.getItem().getTileName().equals("lightSaber")) {
                            cell.getActor().setTileName("player-pick-up-lightSaber");
                        }
                        buttonHandler(this.ui, cell);
                    }
                    Tiles.drawTile(context, cell.getActor(), x, y);

                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    private void buttonHandler( GridPane ui, Cell cell) {
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
            inventoryLabel.setText(map.getPlayer().inventoryForDisplay());

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


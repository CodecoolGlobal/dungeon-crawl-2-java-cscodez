package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.tiles.Tiles;
import com.codecool.dungeoncrawl.util.BuildUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    GameMap map = MapLoader.loadMap("/map.txt");
    GridPane ui = new GridPane();
    BuildUI uiBuilder = new BuildUI();
    int visibleSize = 20;
    Canvas canvas = new Canvas(
            visibleSize * Tiles.TILE_WIDTH,
            visibleSize * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.ui.setPrefWidth(250);
        this.ui.setPadding(new Insets(10));

        this.ui.add(new Label("Health: "), 0, 0);
        this.ui.add(healthLabel, 1, 0);

        //!!!!!!
        //uiBuilder.inventoryDisplayer();

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
                case I:
                    uiBuilder.inventoryDisplayer(ui, map, healthLabel);

            }
        }
        map.moveEnemies();
        if (!map.getPlayer().isAlive()) {
            map.getPlayer().getCell().setType(CellType.GRAVE);
            map.getPlayer().setTileName("dead");

        }
        map.resetEnemiesAttack();
        refresh();
    }


    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int [] startCoordinate = map.getPlayerCoordinate(visibleSize);

        int j = 0;
        int k = 0;

        for (int x = startCoordinate[0]; x < visibleSize + startCoordinate[0]; x++) {
            for (int y = startCoordinate[1]; y < startCoordinate[1] + visibleSize; y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null ) {
                    if (cell.getItem() != null && cell.getActor() instanceof Player) {
                        uiBuilder.pickUpButtonHandler(this.ui, cell, map);
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
        healthLabel.setText("" + map.getPlayer().getHealth());
        if(checkIfDoorIsOpen()){
            Player player = map.getPlayer();
            renderNewMap(player);
        }
    }



    private boolean checkIfDoorIsOpen() {
        for (Cell[] cellRow : map.getCells()) {
            for (Cell cell : cellRow) {
                if(cell.getType().equals(CellType.OPENED_DOOR)){
                    return true;
                }
            }
        }
        return false;
    }

    private void renderNewMap(Player player){
        map = MapLoader.loadMap("/map2.txt");
        Cell playerCell = map.getPlayer().getCell();
        player.setCell(playerCell);
        map.setPlayer(player);
    }
}


package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemies.*;
import com.codecool.dungeoncrawl.logic.items.*;


import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapSource) {
        InputStream is = MapLoader.class.getResourceAsStream(mapSource);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '*':
                            cell.setType(CellType.BLUE_WALL);
                            break;
                        case '<':
                            cell.setType(CellType.BLUE_WALL_PIPE);
                            break;
                        case '>':
                            cell.setType(CellType.BLUE_WALL_PIPE_2);
                            break;
                        case 'f':
                            cell.setType(CellType.FLOOR2);
                            break;
                        case 'c':
                            cell.setType(CellType.CARGO);
                            break;
                        case 'E':
                            cell.setType(CellType.EXIT_1);
                            break;
                        case 'X':
                            cell.setType(CellType.EXIT_2);
                            break;
                        case 'I':
                            cell.setType(CellType.EXIT_3);
                            break;
                        case 'T':
                            cell.setType(CellType.EXIT_4);
                            break;
                        case '1':
                            cell.setType(CellType.HYDROCAPSULE_1);
                            break;
                        case '2':
                            cell.setType(CellType.HYDROCAPSULE_2);
                            break;
                        case '|':
                            cell.setType(CellType.SINGLE_PIPE);
                            break;
                        case '_':
                            cell.setType(CellType.PIPE_END);
                            break;
                        case 'A':
                            cell.setType(CellType.BLUE_WALL_BLOCK);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Stormtrooper(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR2);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'L':
                            cell.setType(CellType.FLOOR);
                            new LightSaber(cell, "lightSaber");
                            break;
                        case 'd':
                            cell.setType(CellType.CLOSED_DOOR);
                            new Door(cell, 1);
                            break;
                        case 'j':
                            cell.setType(CellType.FLOOR);
                            new Jawa(cell);
                            break;
                        case 'G':
                            cell.setType(CellType.FLOOR);
                            new GreenBrute(cell);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new BlueMilk(cell, "blueMilk");
                            break;
                        case 'H':
                            cell.setType(CellType.FLOOR);
                            new Hologram(cell, "hologram");
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            new KeyCard(cell, "keyCard");
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new Mandalorian(cell);
                            break;

                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}

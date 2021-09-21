package com.codecool.dungeoncrawl.logic.items;

public class Item {
    private String name;
    private String tileName;

    Item(String name, String tileName){
        this.name = name;
        this.tileName = tileName;
    }
    public String getName() {return name;}
    public String getTileName() {return tileName;}

}

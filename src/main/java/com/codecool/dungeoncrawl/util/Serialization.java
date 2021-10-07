package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serialization {

    private static Gson gson = new Gson();
    private static final GsonBuilder gsonBuilder = new GsonBuilder()
;
    public static String serialize(Object object) {
        gsonBuilder.registerTypeAdapter(Item.class, new InterfaceAdapter<Item>());
        gsonBuilder.registerTypeAdapter(Actor.class, new InterfaceAdapter<Actor>());
        gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    public static <T> Object deserialize(String json, Class<T> classofT) {
        gsonBuilder.registerTypeAdapter(Item.class, new InterfaceAdapter<Item>());
        gsonBuilder.registerTypeAdapter(Actor.class, new InterfaceAdapter<Actor>());
        gson = gsonBuilder.create();

        return gson.fromJson(json, classofT);
    }
}

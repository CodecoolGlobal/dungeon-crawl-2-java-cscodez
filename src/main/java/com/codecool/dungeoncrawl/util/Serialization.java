package com.codecool.dungeoncrawl.util;

import com.google.gson.Gson;

public class Serialization {

    private static final Gson gson = new Gson();

    public static String serialize(Object object) {
        return gson.toJson(object);
    }

    public static <T> Object deserialize(String json, Class<T> classofT) {
        return gson.fromJson(json, classofT);
    }
}

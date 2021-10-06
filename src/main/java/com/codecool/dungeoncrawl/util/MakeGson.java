package com.codecool.dungeoncrawl.util;

import com.google.gson.Gson;

public class MakeGson {

    private static final Gson gson = new Gson();

    public static String convertObjectToJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> Object convertJsonToObject(String json, Class<T> classofT) {
        return gson.fromJson(json, classofT);
    }
}

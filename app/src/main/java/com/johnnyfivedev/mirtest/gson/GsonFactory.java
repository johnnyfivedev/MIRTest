package com.johnnyfivedev.mirtest.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

public class GsonFactory {

    public static Gson getNetworkGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(DateTime.class, new IsoDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new IsoLocalDateTimeDeserializer());
        return gsonBuilder.create();
    }
}
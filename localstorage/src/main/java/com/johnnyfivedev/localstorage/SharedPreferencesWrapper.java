package com.johnnyfivedev.localstorage;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesWrapper {

    private static String sharedPreferencesName = "ParkingSharedPreferences";
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences() {
        if (sharedPreferences != null) {
            return sharedPreferences;
        } else {
            throw new IllegalStateException("SharedPreferences not initialized");
        }
    }
}
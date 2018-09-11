package com.johnnyfivedev.localstorage;

import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;


public class LocalStorageServiceImpl implements LocalStorageService {

    private final SharedPreferences sharedPreferences;


    public LocalStorageServiceImpl() {
        sharedPreferences = SharedPreferencesWrapper.getSharedPreferences();
    }

    //region ===================== Implementation ======================

    @Override
    public String getData(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    @Override
    public void saveData(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * see https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences/14034804#14034804
     */
    @Override
    public Set<String> getSetData(String key) {
        Set<String> newSet = new HashSet<>();
        Set<String> dataSet = sharedPreferences.getStringSet(key, null);
        if (dataSet != null) {
            newSet.addAll(dataSet);
            return newSet;
        }
        return null;
    }

    @Override
    public void saveSet(String key, Set<String> set) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, set);
        editor.apply();
    }

    @Override
    public void clearData(String key) {
        saveData(key, null);
    }

    @Override
    public void addToSet(String key, String value) {
        if (!sharedPreferences.contains(key)) {
            Set<String> newSet = new HashSet<>();
            saveSet(key, newSet);
        }

        Set<String> setStrings = new HashSet<>(sharedPreferences.getStringSet(key, new HashSet<String>()));
        setStrings.add(value);
        saveSet(key, setStrings);
    }

    @Override
    public boolean contains(String key, String value) {
        if (!sharedPreferences.contains(key) || sharedPreferences.getStringSet(key, null) == null) {
            return false;
        }
        Set<String> set = sharedPreferences.getStringSet(key, null);
        return set.contains(value);
    }

    @Override
    public void print(String key, boolean isString) {
        if (isString) {
            Object dataString = sharedPreferences.getString(key, null);
        } else {
            Set<String> dataSet = sharedPreferences.getStringSet(key, null);
            int index = 0;
            if (dataSet != null) {
                for (String str : dataSet) {
                    index++;
                }
            }
        }
    }

    //endregion
}

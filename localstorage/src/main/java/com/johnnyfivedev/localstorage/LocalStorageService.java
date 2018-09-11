package com.johnnyfivedev.localstorage;

import java.util.Set;


public interface LocalStorageService {

    String getData(String key, String defaultValue);

    void saveData(String key, String value);

    void clearData(String key);

    Set<String> getSetData(String key);

    void saveSet(String key, Set<String> set);

    void addToSet(String key, String value);

    boolean contains(String key, String value);

    void print(String key, boolean isString);
}

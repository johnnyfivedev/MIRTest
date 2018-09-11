package com.johnnyfivedev.localstorage;


import io.reactivex.Completable;
import io.reactivex.Observable;


public interface ObservableLocalStorageService {

    Observable<String> getData(final String key, final String defaultValue);

    Completable saveData(final String key, final String value);
}
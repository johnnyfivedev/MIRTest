package com.johnnyfivedev.mirtest;

import android.app.Application;

import com.johnnyfivedev.localstorage.SharedPreferencesWrapper;
import com.johnnyfivedev.mirtest.di.application.ApplicationComponent;
import com.johnnyfivedev.mirtest.di.application.ApplicationModule;
import com.johnnyfivedev.mirtest.di.application.DaggerApplicationComponent;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;


public class MirApplication extends Application {

    private static MirApplication instance;

    private ApplicationComponent applicationComponent;
    private Cicerone<Router> cicerone;

    //region ===================== Instance ======================

    public static MirApplication getInstance() {
        return instance;
    }

    //endregion

    //region ===================== Lifecycle ======================

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initInjector();
        initSharedPreferencesWrapper();
        initCicerone();
    }

    //endregion

    //region ===================== Getters ======================

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public Cicerone<Router> getCicerone() {
        return cicerone;
    }


    //endregion

    //region ===================== Internal ======================

    private void initInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    private void initSharedPreferencesWrapper() {
        SharedPreferencesWrapper.init(this);
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    //endregion
}
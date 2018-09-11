package com.johnnyfivedev.mirtest;

import android.app.Application;

import com.johnnyfivedev.localstorage.SharedPreferencesWrapper;
import com.johnnyfivedev.mirtest.di.application.ApplicationComponent;
import com.johnnyfivedev.mirtest.di.application.ApplicationModule;
import com.johnnyfivedev.mirtest.di.application.DaggerApplicationComponent;


public class MirApplication extends Application {

    private ApplicationComponent applicationComponent;
    public static MirApplication instance;


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
    }

    //endregion

    //region ===================== Getters ======================

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
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

    //endregion
}
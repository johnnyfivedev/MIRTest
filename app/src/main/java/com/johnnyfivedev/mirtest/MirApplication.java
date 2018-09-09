package com.johnnyfivedev.mirtest;

import android.app.Application;
import android.content.Context;

import com.johnnyfivedev.mirtest.di.application.ApplicationComponent;
import com.johnnyfivedev.mirtest.di.application.ApplicationModule;
import com.johnnyfivedev.mirtest.di.application.DaggerApplicationComponent;

import io.reactivex.plugins.RxJavaPlugins;


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
        //ComponentStore.getInstance().setApplicationComponent(this.applicationComponent);
    }


    //endregion
}
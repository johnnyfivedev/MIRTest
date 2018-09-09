package com.johnnyfivedev.mirtest.ui.activity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.johnnyfivedev.mirtest.MirApplication;
import com.johnnyfivedev.mirtest.di.application.ApplicationComponent;


public abstract class BaseMvpAppCompatActivity extends MvpAppCompatActivity {

    public ApplicationComponent getAppComponent() {
        return ((MirApplication) getApplication()).getApplicationComponent();
    }
}
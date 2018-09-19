package com.johnnyfivedev.mirtest.ui.activity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.johnnyfivedev.mirtest.MirApplication;
import com.johnnyfivedev.mirtest.di.application.ApplicationComponent;

import ru.terrakok.cicerone.android.SupportAppNavigator;


public abstract class BaseMvpAppCompatActivity extends MvpAppCompatActivity {

    public ApplicationComponent getAppComponent() {
        return ((MirApplication) getApplication()).getApplicationComponent();
    }

    public abstract SupportAppNavigator getNavigator();

    //region ===================== Lifecycle ======================

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        MirApplication.getInstance()
                .getCicerone()
                .getNavigatorHolder()
                .setNavigator(getNavigator());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MirApplication.getInstance()
                .getCicerone()
                .getNavigatorHolder()
                .removeNavigator();
    }

    //endregion
}
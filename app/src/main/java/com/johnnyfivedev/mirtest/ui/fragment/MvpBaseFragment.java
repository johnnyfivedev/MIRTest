package com.johnnyfivedev.mirtest.ui.fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.johnnyfivedev.mirtest.MirApplication;
import com.johnnyfivedev.mirtest.di.application.ApplicationComponent;


public abstract class MvpBaseFragment extends MvpAppCompatFragment {

    private ApplicationComponent appComponent;

    public ApplicationComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = MirApplication.instance.getApplicationComponent();
        }
        return this.appComponent;
    }

    //endregion
}
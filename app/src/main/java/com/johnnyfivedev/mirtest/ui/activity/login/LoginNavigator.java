package com.johnnyfivedev.mirtest.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.johnnyfivedev.mirtest.exception.NoSuchScreenKeyException;
import com.johnnyfivedev.mirtest.ui.Screens;
import com.johnnyfivedev.mirtest.ui.activity.newscontainer.NewsContainerActivity;

import ru.terrakok.cicerone.android.SupportAppNavigator;

public class LoginNavigator extends SupportAppNavigator {

    public LoginNavigator(FragmentActivity activity, int containerId) {
        super(activity, containerId);
    }

    @Override
    protected Intent createActivityIntent(Context context, String screenKey, Object data) {
        switch (screenKey) {
            case Screens.CONTAINER_NEWS:
                return new Intent(context, NewsContainerActivity.class);
            default:
                throw new NoSuchScreenKeyException(screenKey);
        }
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        return null;
    }
}

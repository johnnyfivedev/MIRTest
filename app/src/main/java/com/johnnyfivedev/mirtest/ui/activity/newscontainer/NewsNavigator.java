package com.johnnyfivedev.mirtest.ui.activity.newscontainer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.johnnyfivedev.mirtest.exception.NoSuchScreenKeyException;
import com.johnnyfivedev.mirtest.ui.Screens;
import com.johnnyfivedev.mirtest.ui.fragment.NewsDetailedFragment;
import com.johnnyfivedev.mirtest.ui.fragment.NewsFragment;

import ru.terrakok.cicerone.android.SupportAppNavigator;

public class NewsNavigator extends SupportAppNavigator {

    public NewsNavigator(FragmentActivity activity, int containerId) {
        super(activity, containerId);
    }

    @Override
    protected Intent createActivityIntent(Context context, String screenKey, Object data) {
       /* switch (screenKey) {
            default:
                throw new NoSuchScreenKeyException(screenKey);
        }*/
       return null;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.NEWS:
                return NewsFragment.newInstance();
            case Screens.NEWS_DETAILED:
                return NewsDetailedFragment.newInstance((Long) data);
            default:
                throw new NoSuchScreenKeyException(screenKey);
        }
    }
}

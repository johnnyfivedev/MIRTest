package com.johnnyfivedev.mirtest.di.feature.newscontainer;

import android.support.v4.app.FragmentActivity;

import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsContainerPresenter;
import com.johnnyfivedev.mirtest.ui.activity.newscontainer.NewsNavigator;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;

@Module
public class NewsContainerModule {

    private final FragmentActivity activity;
    private final int fragmentContainerId;


    public NewsContainerModule(FragmentActivity activity, int fragmentContainerId) {
        this.activity = activity;
        this.fragmentContainerId = fragmentContainerId;
    }

    @Provides
    @NewsScope
    NewsContainerPresenter providePresenter(Router router) {
        return new NewsContainerPresenter(router);
    }

    @Provides
    @NewsScope
    SupportAppNavigator provideNewsNavigator() {
        return new NewsNavigator(activity, fragmentContainerId);
    }
}

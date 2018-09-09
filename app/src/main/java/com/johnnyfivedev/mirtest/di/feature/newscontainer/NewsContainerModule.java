package com.johnnyfivedev.mirtest.di.feature.newscontainer;

import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsContainerPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsContainerModule {

    @Provides
    @NewsScope
    NewsContainerPresenter providePresenter() {
        return new NewsContainerPresenter();
    }
}

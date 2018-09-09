package com.johnnyfivedev.mirtest.di.feature.news;

import android.content.Context;

import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsPresenter;
import com.johnnyfivedev.mirtest.ui.adapter.NewsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    private Context context;


    public NewsModule(Context context) {
        this.context = context;
    }

    @Provides
    @NewsScope
    NewsPresenter providePresenter() {
        return new NewsPresenter();
    }

    @Provides
    @NewsScope
    NewsAdapter provideNewsAdapter() {
        return new NewsAdapter(context);
    }
}

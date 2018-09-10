package com.johnnyfivedev.mirtest.di.feature.news;

import android.content.Context;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.data.api.LoginApi;
import com.johnnyfivedev.data.repositoryimpl.NewsRepositoryImpl;
import com.johnnyfivedev.domain.repository.NewsRepository;
import com.johnnyfivedev.domain.usecase.news.GetNewsUseCase;
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
    NewsPresenter providePresenter(GetNewsUseCase getNewsUseCase) {
        return new NewsPresenter(getNewsUseCase);
    }

    @Provides
    @NewsScope
    NewsAdapter provideNewsAdapter() {
        return new NewsAdapter(context);
    }

    @Provides
    @NewsScope
    NewsRepository provideNewsRepository(Api api) {
        return new NewsRepositoryImpl(api);
    }
}

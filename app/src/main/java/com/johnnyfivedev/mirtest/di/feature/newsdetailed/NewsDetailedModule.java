package com.johnnyfivedev.mirtest.di.feature.newsdetailed;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.data.repositoryimpl.NewsRepositoryImpl;
import com.johnnyfivedev.domain.repository.NewsRepository;
import com.johnnyfivedev.domain.usecase.news.GetNewsItemById;
import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsDetailedPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class NewsDetailedModule {

    private Long newsItemId;


    public NewsDetailedModule(Long newsItemId) {
        this.newsItemId = newsItemId;
    }

    @Provides
    @NewsScope
    NewsDetailedPresenter providePresenter(GetNewsItemById getNewsItemById) {
        return new NewsDetailedPresenter(newsItemId, getNewsItemById);
    }

    @Provides
    @NewsScope
    NewsRepository provideNewsRepository(Api api) {
        return new NewsRepositoryImpl(api, newsDataSource);
    }
}

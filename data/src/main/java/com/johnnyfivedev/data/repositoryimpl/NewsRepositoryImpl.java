package com.johnnyfivedev.data.repositoryimpl;

import android.arch.paging.PagedList;

import com.johnnyfivedev.data.MainThreadExecutor;
import com.johnnyfivedev.data.NewsDataSource;
import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.repository.NewsRepository;

import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.Observable;

public class NewsRepositoryImpl implements NewsRepository {

    private final Api api;
    private final NewsDataSource newsDataSource;


    public NewsRepositoryImpl(Api api,
                              NewsDataSource newsDataSource) {
        this.api = api;
        this.newsDataSource = newsDataSource;
    }

    @Override
    public Observable<List<NewsItem>> getNews() {
        return api.getNews().map(newsResponse -> newsResponse.getData().getNews());
    }

    @Override
    public Observable<List<NewsItem>> getNewsPaging() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        PagedList<NewsItem> pagedList = new PagedList.Builder<>(newsDataSource, config)
                .setNotifyExecutor(new MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();
    }

    @Override
    public Observable<NewsItem> getNewsItemById(Long id) {
        return api.getNewsById(id).map(newsItemResponse -> newsItemResponse.getData().getNewsItem());
    }
}

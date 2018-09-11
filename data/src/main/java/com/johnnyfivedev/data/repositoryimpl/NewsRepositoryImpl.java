package com.johnnyfivedev.data.repositoryimpl;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.repository.NewsRepository;

import java.util.List;

import io.reactivex.Observable;

public class NewsRepositoryImpl implements NewsRepository {

    private final Api api;


    public NewsRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Observable<List<NewsItem>> getNews() {
        return api.getNews().map(newsResponse -> newsResponse.getData().getNews());
    }

    @Override
    public Observable<NewsItem> getNewsItemById(Long id) {
        return api.getNewsById(id).map(newsItemResponse -> newsItemResponse.getData().getNewsItem());
    }
}

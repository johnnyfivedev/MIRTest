package com.johnnyfivedev.domain.repository;

import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

import io.reactivex.Observable;

public interface NewsRepository {

    Observable<List<NewsItem>> getNews();

    Observable<List<NewsItem>> getNewsPaging(int startPosition, int pageSize);

    Observable<NewsItem> getNewsItemById(Long id);
}
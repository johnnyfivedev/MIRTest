package com.johnnyfivedev.domain.usecase.news;

import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.repository.NewsRepository;
import com.johnnyfivedev.domain.usecase.base.ObservableUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetNewsItemById extends ObservableUseCase<Long, NewsItem> {

    private final NewsRepository newsRepository;


    @Inject
    public GetNewsItemById(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Observable<NewsItem> buildUseCaseObservable(Long id) {
        return newsRepository.getNewsItemById(id);
    }
}
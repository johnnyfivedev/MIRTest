package com.johnnyfivedev.domain.usecase.news;

import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.repository.NewsRepository;
import com.johnnyfivedev.domain.usecase.base.ObservableUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetNewsUseCase extends ObservableUseCase<Void, List<NewsItem>> {

    private final NewsRepository newsRepository;


    @Inject
    public GetNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Observable<List<NewsItem>> buildUseCaseObservable(Void aVoid) {
        return newsRepository.getNews();
    }
}
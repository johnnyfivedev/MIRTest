package com.johnnyfivedev.mirtest.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

public interface NewsView extends MvpView {
    void showNews(List<NewsItem> news);
}

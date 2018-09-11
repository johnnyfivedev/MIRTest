package com.johnnyfivedev.mirtest.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.johnnyfivedev.domain.entity.news.NewsItem;

public interface NewsDetailedView extends MvpView {
    void showNewsItem(NewsItem newsItem);

    void closeScreen();

    void openSource(String sourceUrl);
}

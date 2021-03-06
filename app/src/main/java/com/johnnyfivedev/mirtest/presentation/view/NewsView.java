package com.johnnyfivedev.mirtest.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NewsView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setNews(List<NewsItem> newsItems);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void buildNewsPaging();
}
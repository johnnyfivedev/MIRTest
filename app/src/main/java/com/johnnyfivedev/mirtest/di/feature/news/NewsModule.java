package com.johnnyfivedev.mirtest.di.feature.news;

import android.content.Context;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.data.repositoryimpl.NewsRepositoryImpl;
import com.johnnyfivedev.domain.entity.news.NewsItem;
import com.johnnyfivedev.domain.repository.NewsRepository;
import com.johnnyfivedev.domain.usecase.news.GetNewsPageUseCase;
import com.johnnyfivedev.mirtest.ListItemClickListener;
import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsPresenter;
import com.johnnyfivedev.mirtest.ui.adapter.news.NewsItemDiffUtilCallback;
import com.johnnyfivedev.mirtest.ui.adapter.news.NewsPagingAdapter;
import com.johnnyfivedev.mirtest.ui.adapter.news.OnNextPageRequestedCallback;
import com.johnnyfivedev.mirtest.ui.adapter.news.PagedListInitializer;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;


@Module
public class NewsModule {

    private final Context context;
    private final ListItemClickListener listItemClickListener;
    private final OnNextPageRequestedCallback onNextPageRequestedCallback;


    public NewsModule(Context context,
                      ListItemClickListener listItemClickListener,
                      OnNextPageRequestedCallback onNextPageRequestedCallback) {
        this.context = context;
        this.listItemClickListener = listItemClickListener;
        this.onNextPageRequestedCallback = onNextPageRequestedCallback;
    }

    @Provides
    @NewsScope
    NewsPresenter providePresenter(Router router, GetNewsPageUseCase getNewsPageUseCase) {
        return new NewsPresenter(router, getNewsPageUseCase);
    }

    @Provides
    @NewsScope
    NewsPagingAdapter provideNewsPagingAdapter(NewsItemDiffUtilCallback diffUtilCallback) {
        return new NewsPagingAdapter(diffUtilCallback, listItemClickListener);
    }

    @Provides
    @NewsScope
    PagedListInitializer<NewsItem> provideNewsPagedListBuilderHolder() {
        return new PagedListInitializer<>(onNextPageRequestedCallback);
    }

    @Provides
    @NewsScope
    NewsRepository provideNewsRepository(Api api) {
        return new NewsRepositoryImpl(api);
    }
}

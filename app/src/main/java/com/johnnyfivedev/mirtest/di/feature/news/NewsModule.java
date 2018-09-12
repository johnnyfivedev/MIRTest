package com.johnnyfivedev.mirtest.di.feature.news;

import android.content.Context;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.data.repositoryimpl.NewsRepositoryImpl;
import com.johnnyfivedev.domain.repository.NewsRepository;
import com.johnnyfivedev.domain.usecase.news.GetNewsPageUseCase;
import com.johnnyfivedev.domain.usecase.news.GetNewsUseCase;
import com.johnnyfivedev.mirtest.ListItemClickListener;
import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.presentation.presenter.NewsPresenter;
import com.johnnyfivedev.mirtest.ui.adapter.news.NewsItemDiffUtilCallback;
import com.johnnyfivedev.mirtest.ui.adapter.news.NewsPagingAdapter;

import dagger.Module;
import dagger.Provides;


@Module
public class NewsModule {

    private Context context;
    private ListItemClickListener listItemClickListener;


    public NewsModule(Context context,
                      ListItemClickListener listItemClickListener) {
        this.context = context;
        this.listItemClickListener = listItemClickListener;
    }

    @Provides
    @NewsScope
    NewsPresenter providePresenter(GetNewsPageUseCase getNewsPageUseCase) {
        return new NewsPresenter(getNewsPageUseCase);
    }

   /* @Provides
    @NewsScope
    NewsAdapter provideNewsAdapter() {
        return new NewsAdapter(context, listItemClickListener);
    }*/

    @Provides
    @NewsScope
    NewsPagingAdapter provideNewsPagingAdapter(NewsItemDiffUtilCallback diffUtilCallback) {
        return new NewsPagingAdapter(diffUtilCallback, listItemClickListener);
    }

    @Provides
    @NewsScope
    NewsRepository provideNewsRepository(Api api) {
        return new NewsRepositoryImpl(api);
    }
}

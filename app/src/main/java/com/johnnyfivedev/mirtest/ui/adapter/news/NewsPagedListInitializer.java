package com.johnnyfivedev.mirtest.ui.adapter.news;

import android.arch.paging.PagedList;

import com.johnnyfivedev.data.MainThreadExecutor;

import java.util.concurrent.Executors;

public class NewsPagedListInitializer {

    private final NewsPagingDataSource dataSource;
    private final PagedList.Builder builder;

    private PagedList pagedList;


    public NewsPagedListInitializer(NewsPagingDataSource.OnNextPageRequestedCallback callback) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        dataSource = new NewsPagingDataSource(callback);

        builder = new PagedList.Builder<>(dataSource, config)
                .setNotifyExecutor(new MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor());
    }

    public NewsPagingDataSource getDataSource() {
        return dataSource;
    }

    public PagedList buildPagedList() {
        pagedList = builder.build();
        return pagedList;
    }
}

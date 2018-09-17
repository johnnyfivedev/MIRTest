package com.johnnyfivedev.mirtest.ui.adapter.news;

import android.arch.paging.PagedList;

import com.johnnyfivedev.data.MainThreadExecutor;

import java.util.List;
import java.util.concurrent.Executors;

public class PagedListInitializer<PagedListType> {

    private final BasePagingDataSource<PagedListType> dataSource;
    private final PagedList.Builder<Integer, PagedListType> builder;

    //private PagedList<PagedListType> pagedList;


    public PagedListInitializer(OnNextPageRequestedCallback onNextPageRequestedCallback) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        this.dataSource = new PagingDataSource<>(onNextPageRequestedCallback);

        builder = new PagedList.Builder<>(dataSource, config)
                .setNotifyExecutor(new MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor());
    }

    public PagedList<PagedListType> buildPagedList() {
        return builder.build();
    }

    public void setItems(List<PagedListType> list) {
        dataSource.setItems(list);
    }
}

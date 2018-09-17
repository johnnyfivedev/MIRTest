package com.johnnyfivedev.mirtest.ui.adapter.news;

import android.arch.paging.PositionalDataSource;

import java.util.List;

public abstract class BasePagingDataSource<PagedListType> extends PositionalDataSource<PagedListType> {

    protected boolean isInitialLoad = true;

    protected PositionalDataSource.LoadInitialCallback<PagedListType> initialCallback;
    protected LoadRangeCallback<PagedListType> rangeCallback;


    public void setItems(List<PagedListType> items) {
        if (isInitialLoad) {
            initialCallback.onResult(items, 0);
        } else {
            rangeCallback.onResult(items);
        }
    }
}

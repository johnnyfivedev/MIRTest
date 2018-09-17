package com.johnnyfivedev.mirtest.ui.adapter.news;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

public class PagingDataSource<PagedListType> extends BasePagingDataSource<PagedListType> {

    private final OnNextPageRequestedCallback onNextPageRequestedCallback;


    public PagingDataSource(OnNextPageRequestedCallback onNextPageRequestedCallback) {
        this.onNextPageRequestedCallback = onNextPageRequestedCallback;
    }

    //region ===================== Override ======================

    @Override
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams params, @NonNull LoadInitialCallback<PagedListType> callback) {
        isInitialLoad = true;
        onNextPageRequestedCallback.onNextPageRequested(1, params.pageSize);
        initialCallback = callback;
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<PagedListType> callback) {
        isInitialLoad = false;
        onNextPageRequestedCallback.onNextPageRequested(params.startPosition / params.loadSize + 1, params.loadSize);
        rangeCallback = callback;
    }

    //endregion
}

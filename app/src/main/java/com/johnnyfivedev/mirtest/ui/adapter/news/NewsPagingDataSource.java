package com.johnnyfivedev.mirtest.ui.adapter.news;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

public class NewsPagingDataSource extends PositionalDataSource<NewsItem> {

    private final OnNextPageRequestedCallback onNextPageRequestedCallback;

    private LoadInitialCallback<NewsItem> initialCallback;
    private LoadRangeCallback<NewsItem> rangeCallback;

    private boolean isInitialLoad = true;


    public NewsPagingDataSource(OnNextPageRequestedCallback onNextPageRequestedCallback) {
        this.onNextPageRequestedCallback = onNextPageRequestedCallback;
    }

    //region ===================== Override ======================

    @Override
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams params, @NonNull LoadInitialCallback<NewsItem> callback) {
        isInitialLoad = true;
        onNextPageRequestedCallback.onNextPageRequested(1, params.pageSize);
        initialCallback = callback;
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<NewsItem> callback) {
        isInitialLoad = false;
        onNextPageRequestedCallback.onNextPageRequested(params.startPosition / params.loadSize + 1, params.loadSize);
        rangeCallback = callback;
    }

    //endregion

    //region ===================== Setters ======================

    public void setItems(List<NewsItem> items) {
        if (isInitialLoad) {
            initialCallback.onResult(items, 0);
        } else {
            rangeCallback.onResult(items);
        }
    }

    //endregion

    //region ===================== Callback ======================

    public interface OnNextPageRequestedCallback {
        void onNextPageRequested(int page, int pageSize);
    }

    //endregion
}

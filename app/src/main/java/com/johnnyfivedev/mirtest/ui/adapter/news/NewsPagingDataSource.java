package com.johnnyfivedev.mirtest.ui.adapter.news;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

public class NewsPagingDataSource extends PositionalDataSource<NewsItem> {

    private final OnDataRequestedCallback onDataRequestedCallback;

    private LoadInitialCallback<NewsItem> initialCallback;
    private LoadRangeCallback<NewsItem> rangeCallback;


    public NewsPagingDataSource(OnDataRequestedCallback onDataRequestedCallback) {
        this.onDataRequestedCallback = onDataRequestedCallback;
    }

    //region ===================== Override ======================

    @Override
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams params, @NonNull LoadInitialCallback<NewsItem> callback) {
        onDataRequestedCallback.onNewsPageRequested(true, params.requestedStartPosition, params.pageSize);
        initialCallback = callback;
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<NewsItem> callback) {
        onDataRequestedCallback.onNewsPageRequested(false, params.startPosition, params.loadSize);
        rangeCallback = callback;
    }

    //endregion

    //region ===================== Setters ======================

    public void setInitial(List<NewsItem> items) {
        initialCallback.onResult(items, 0);
    }

    public void setRange(List<NewsItem> news) {
        rangeCallback.onResult(news);
    }

    public void setItems(List<NewsItem> items, boolean initialRequest) {
        if (initialRequest) {
            initialCallback.onResult(items, 0);
        } else {
            rangeCallback.onResult(items);
        }
    }

    //endregion

    //region ===================== Callback ======================

    public interface OnDataRequestedCallback {
        void onNewsPageRequested(boolean initialRequest, int startPosition, int pageSize);
    }

    //endregion
}
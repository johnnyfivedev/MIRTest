package com.johnnyfivedev.data;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

public class NewsDataSource extends PositionalDataSource<NewsItem> {

    private final Api api;

    public NewsDataSource(Api api) {
        this.api = api;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<NewsItem> callback) {
       /* Log.d(TAG, "loadInitial, requestedStartPosition = " + params.requestedStartPosition +
                ", requestedLoadSize = " + params.requestedLoadSize);*/


        List<NewsItem> result = api.getNewsPaging(params.requestedStartPosition, params.requestedLoadSize);
        callback.onResult(result, 0);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<NewsItem> callback) {
        //Log.d(TAG, "loadRange, startPosition = " + params.startPosition + ", loadSize = " + params.loadSize);
        List<NewsItem> result = newsStorage.getData(params.startPosition, params.loadSize);
        callback.onResult(result);
    }
}
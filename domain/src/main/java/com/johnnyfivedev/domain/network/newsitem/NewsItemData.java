package com.johnnyfivedev.domain.network.newsitem;

import com.google.gson.annotations.SerializedName;
import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

public class NewsItemData {

    @SerializedName("news")
    private NewsItem newsItem;


    //region ===================== Getters ======================

    public NewsItem getNewsItem() {
        return newsItem;
    }

    //endregion
}

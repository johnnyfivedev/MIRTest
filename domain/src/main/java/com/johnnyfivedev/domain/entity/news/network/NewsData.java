package com.johnnyfivedev.domain.entity.news.network;

import com.google.gson.annotations.SerializedName;
import com.johnnyfivedev.domain.entity.news.NewsItem;

import java.util.List;

public class NewsData {

    @SerializedName("news")
    private List<NewsItem> news;


    //region ===================== Getters ======================

    public List<NewsItem> getNews() {
        return news;
    }

    //endregion
}

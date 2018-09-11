package com.johnnyfivedev.domain.network.news;

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

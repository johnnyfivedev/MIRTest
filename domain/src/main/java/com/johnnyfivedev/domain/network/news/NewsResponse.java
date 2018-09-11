package com.johnnyfivedev.domain.network.news;

import com.google.gson.annotations.SerializedName;

public class NewsResponse {

    @SerializedName("data")
    private NewsData data;


    //region ===================== Getters ======================

    public NewsData getData() {
        return data;
    }

    //endregion
}

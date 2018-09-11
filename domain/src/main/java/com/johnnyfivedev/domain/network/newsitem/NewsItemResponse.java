package com.johnnyfivedev.domain.network.newsitem;

import com.google.gson.annotations.SerializedName;

public class NewsItemResponse {

    @SerializedName("data")
    private NewsItemData data;


    //region ===================== Getters ======================

    public NewsItemData getData() {
        return data;
    }

    //endregion
}
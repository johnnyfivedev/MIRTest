package com.johnnyfivedev.domain.entity.news;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsItem {

    private long id;

    private String title;

    private String text;

    @SerializedName("created_at")
    private DateTime creationDate;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("news_category")
    private NewsCategory newsCategory;

    @SerializedName("source")
    private String sourceUrl;


    //region ===================== Getters ======================

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreationDateFormatted() {
        SimpleDateFormat spf = new SimpleDateFormat("dd MMM. yyyy, HH:mm");
        return spf.format(creationDate.getMillis());
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    //endregion
}

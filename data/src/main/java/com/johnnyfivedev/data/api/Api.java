package com.johnnyfivedev.data.api;

import com.johnnyfivedev.domain.network.news.NewsResponse;
import com.johnnyfivedev.domain.network.newsitem.NewsItemResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("news?page=1per=10")
    Observable<NewsResponse> getNews();

    @GET("news?")
    Observable<NewsResponse> getNewsPaging(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("news/{id}")
    Observable<NewsItemResponse> getNewsById(@Path("id") Long id);
}
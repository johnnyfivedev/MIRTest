package com.johnnyfivedev.data.api;

import com.johnnyfivedev.domain.entity.news.network.NewsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("news?page=1per=10")
    Observable<NewsResponse> getNews();
}

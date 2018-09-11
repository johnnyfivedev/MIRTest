package com.johnnyfivedev.mirtest.di.application;

import android.content.Context;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.localstorage.LocalStorageService;
import com.johnnyfivedev.localstorage.LocalStorageServiceImpl;
import com.johnnyfivedev.mirtest.MirApplication;
import com.johnnyfivedev.mirtest.OkHttpClientFactory;
import com.johnnyfivedev.mirtest.ServerUrls;
import com.johnnyfivedev.mirtest.gson.GsonFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return MirApplication.getInstance();
    }

    @Provides
    @Singleton
    Api provideApi() {
        return new Retrofit.Builder()
                .baseUrl(ServerUrls.API_BASE_URL)
                .client(OkHttpClientFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.getNetworkGsonConverter()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }

    @Provides
    @Singleton
    LocalStorageService provideLocalStorageService() {
        return new LocalStorageServiceImpl();
    }
}
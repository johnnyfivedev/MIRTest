package com.johnnyfivedev.mirtest.di.application;

import android.content.Context;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.data.api.LoginApi;
import com.johnnyfivedev.mirtest.MirApplication;
import com.johnnyfivedev.mirtest.ServerUrls;
import com.johnnyfivedev.mirtest.gson.GsonFactory;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    //private final Cicerone<Router> cicerone;

    public ApplicationModule() {
        //this.cicerone = Cicerone.create();
    }

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
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.getNetworkGsonConverter()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }
}
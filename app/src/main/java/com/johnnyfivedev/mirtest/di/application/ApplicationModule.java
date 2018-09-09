package com.johnnyfivedev.mirtest.di.application;

import android.content.Context;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.data.repositoryimpl.LoginRepositoryImpl;
import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.mirtest.MirApplication;
import com.johnnyfivedev.mirtest.ServerUrls;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApplicationModule {

    public static final String LOGIN_API = "LOGIN_API";
    public static final String API = "API";

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
    @Named(LOGIN_API)
    Api provideLoginApi() {
        return new Retrofit.Builder()
                .baseUrl(ServerUrls.LOGIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }

    @Provides
    @Singleton
    @Named(API)
    Api provideApi() {
        return new Retrofit.Builder()
                .baseUrl(ServerUrls.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }

    @Provides
    @Singleton
    LoginRepository provideLoginRepository(@Named(LOGIN_API) Api api) {
        return new LoginRepositoryImpl(api);
    }
}
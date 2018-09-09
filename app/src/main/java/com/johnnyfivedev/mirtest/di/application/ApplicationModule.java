package com.johnnyfivedev.mirtest.di.application;

import android.content.Context;

import com.johnnyfivedev.data.repositoryimpl.LoginRepositoryImpl;
import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.mirtest.MirApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


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
    LoginRepository provideLoginRepository() {
        return new LoginRepositoryImpl();
    }
}
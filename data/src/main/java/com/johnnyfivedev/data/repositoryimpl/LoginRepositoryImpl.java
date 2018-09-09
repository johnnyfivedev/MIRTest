package com.johnnyfivedev.data.repositoryimpl;

import com.johnnyfivedev.data.api.Api;
import com.johnnyfivedev.domain.repository.LoginRepository;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepositoryImpl implements LoginRepository {

    private Api api;

    public LoginRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<Object> login(String login, String password, String grantType) {
        return api.login(login, password, grantType);
    }
}

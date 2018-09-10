package com.johnnyfivedev.data.repositoryimpl;

import com.johnnyfivedev.data.api.LoginApi;
import com.johnnyfivedev.domain.repository.LoginRepository;

import io.reactivex.Single;

public class LoginRepositoryImpl implements LoginRepository {

    private LoginApi loginApi;


    public LoginRepositoryImpl(LoginApi loginApi) {
        this.loginApi = loginApi;
    }

    @Override
    public Single<Object> login(String login, String password, String grantType) {
        return loginApi.login(login, password, grantType);
    }
}

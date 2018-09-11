package com.johnnyfivedev.domain.repository;

import com.johnnyfivedev.domain.entity.login.LoginResult;

import io.reactivex.Single;

public interface LoginRepository {

    Single<LoginResult> login(String login, String password, String grantType);

    Single<Boolean> isAuthorized();
}

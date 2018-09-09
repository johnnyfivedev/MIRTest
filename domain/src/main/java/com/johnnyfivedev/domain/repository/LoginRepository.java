package com.johnnyfivedev.domain.repository;

import io.reactivex.Single;

public interface LoginRepository {

    Single<Object> login(String login, String password, String grantType);
}

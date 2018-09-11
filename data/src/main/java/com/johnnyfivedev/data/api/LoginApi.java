package com.johnnyfivedev.data.api;

import com.johnnyfivedev.domain.entity.login.LoginResult;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {

    @FormUrlEncoded
    @POST("oauth/token")
    Single<LoginResult> login(@Field("username") String login,
                              @Field("password") String password,
                              @Field("grant_type") String grantType);
}

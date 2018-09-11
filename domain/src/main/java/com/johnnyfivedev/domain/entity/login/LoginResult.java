package com.johnnyfivedev.domain.entity.login;

import com.google.gson.annotations.SerializedName;

public class LoginResult {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("refresh_token")
    private String refreshToken;


    //region ===================== Getters ======================

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    //endregion
}

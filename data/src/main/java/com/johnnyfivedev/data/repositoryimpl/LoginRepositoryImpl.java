package com.johnnyfivedev.data.repositoryimpl;

import com.johnnyfivedev.data.api.LoginApi;
import com.johnnyfivedev.domain.entity.login.LoginResult;
import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.localstorage.LocalStorageService;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;

public class LoginRepositoryImpl implements LoginRepository {

    public static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";

    private LoginApi loginApi;
    private LocalStorageService localStorageService;


    public LoginRepositoryImpl(LoginApi loginApi,
                               LocalStorageService localStorageService) {
        this.loginApi = loginApi;
        this.localStorageService = localStorageService;
    }

    @Override
    public Single<LoginResult> login(String login, String password, String grantType) {
        return loginApi.login(login, password, grantType)
                .map(loginResult -> {
                    localStorageService.saveData(KEY_ACCESS_TOKEN, loginResult.getAccessToken());
                    return loginResult;
                });
    }

    @Override
    public Single<Boolean> isAuthorized() {
        return Single.create(emitter -> {
            String accessToken = localStorageService.getData(KEY_ACCESS_TOKEN, null);
            // Считаем авторизованным, если токен есть. Но нужно проверять не протух ли токен
            emitter.onSuccess(accessToken != null);
        });
    }
}

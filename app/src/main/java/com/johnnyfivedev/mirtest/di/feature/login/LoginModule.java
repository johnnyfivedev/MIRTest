package com.johnnyfivedev.mirtest.di.feature.login;

import com.johnnyfivedev.data.api.LoginApi;
import com.johnnyfivedev.data.repositoryimpl.LoginRepositoryImpl;
import com.johnnyfivedev.domain.repository.LoginRepository;
import com.johnnyfivedev.domain.usecase.login.IsAuthorizedUseCase;
import com.johnnyfivedev.domain.usecase.login.LoginUseCase;
import com.johnnyfivedev.localstorage.LocalStorageService;
import com.johnnyfivedev.mirtest.ServerUrls;
import com.johnnyfivedev.mirtest.di.scope.LoginScope;
import com.johnnyfivedev.mirtest.presentation.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class LoginModule {

    @Provides
    @LoginScope
    LoginPresenter providePresenter(LoginUseCase loginUseCase,
                                    IsAuthorizedUseCase isAuthorizedUseCase) {
        return new LoginPresenter(loginUseCase, isAuthorizedUseCase);
    }

    @Provides
    @LoginScope
    LoginApi provideLoginApi() {
        return new Retrofit.Builder()
                .baseUrl(ServerUrls.LOGIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LoginApi.class);
    }

    @Provides
    @LoginScope
    LoginRepository provideLoginRepository(LoginApi api,
                                           LocalStorageService localStorageService) {
        return new LoginRepositoryImpl(api, localStorageService);
    }
}

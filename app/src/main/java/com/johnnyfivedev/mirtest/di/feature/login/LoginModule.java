package com.johnnyfivedev.mirtest.di.feature.login;

import com.johnnyfivedev.domain.usecase.login.LoginUseCase;
import com.johnnyfivedev.mirtest.di.scope.LoginScope;
import com.johnnyfivedev.mirtest.presentation.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @LoginScope
    LoginPresenter providePresenter(LoginUseCase loginUseCase) {
        return new LoginPresenter(loginUseCase);
    }
}

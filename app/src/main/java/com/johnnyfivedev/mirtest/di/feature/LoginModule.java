package com.johnnyfivedev.mirtest.di.feature;

import com.johnnyfivedev.domain.usecase.login.LoginUseCase;
import com.johnnyfivedev.mirtest.di.scope.PresenterScope;
import com.johnnyfivedev.mirtest.presentation.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @PresenterScope
    LoginPresenter providePresenter(LoginUseCase loginUseCase) {
        return new LoginPresenter(loginUseCase);
    }
}

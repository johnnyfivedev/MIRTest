package com.johnnyfivedev.mirtest.di.feature.login;

import com.johnnyfivedev.mirtest.di.scope.LoginScope;
import com.johnnyfivedev.mirtest.ui.activity.login.LoginActivity;

import dagger.Subcomponent;

@LoginScope
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);
}

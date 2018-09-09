package com.johnnyfivedev.mirtest.di.feature;

import com.johnnyfivedev.mirtest.di.scope.PresenterScope;
import com.johnnyfivedev.mirtest.ui.activity.LoginActivity;

import dagger.Subcomponent;

@PresenterScope
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);
}

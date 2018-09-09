package com.johnnyfivedev.mirtest.di.application;

import com.johnnyfivedev.mirtest.di.feature.LoginComponent;
import com.johnnyfivedev.mirtest.di.feature.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */

@Singleton
@Component(modules = {ApplicationModule.class})

public interface ApplicationComponent {

    LoginComponent plus(LoginModule module);

}
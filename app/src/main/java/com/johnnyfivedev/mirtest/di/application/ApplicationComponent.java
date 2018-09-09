package com.johnnyfivedev.mirtest.di.application;

import com.johnnyfivedev.mirtest.di.feature.login.LoginComponent;
import com.johnnyfivedev.mirtest.di.feature.login.LoginModule;
import com.johnnyfivedev.mirtest.di.feature.news.NewsComponent;
import com.johnnyfivedev.mirtest.di.feature.news.NewsModule;
import com.johnnyfivedev.mirtest.di.feature.newscontainer.NewsContainerComponent;
import com.johnnyfivedev.mirtest.di.feature.newscontainer.NewsContainerModule;
import com.johnnyfivedev.mirtest.ui.fragment.NewsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */

@Singleton
@Component(modules = {ApplicationModule.class})

public interface ApplicationComponent {

    LoginComponent plus(LoginModule module);

    NewsContainerComponent plus(NewsContainerModule module);

    NewsComponent plus(NewsModule module);
}
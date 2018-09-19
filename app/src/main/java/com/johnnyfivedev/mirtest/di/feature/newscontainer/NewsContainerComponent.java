package com.johnnyfivedev.mirtest.di.feature.newscontainer;

import com.johnnyfivedev.mirtest.di.scope.NewsScope;
import com.johnnyfivedev.mirtest.ui.activity.newscontainer.NewsContainerActivity;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = {NewsContainerModule.class})
public interface NewsContainerComponent {

    void inject(NewsContainerActivity activity);
}